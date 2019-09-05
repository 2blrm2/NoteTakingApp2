package com.example.notetakingapp.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.notetakingapp.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    public static final String TAG = "MyTag";
    private static AppRepository ourInstance;
    private AppDatabase mDatabase;
    public LiveData<List<NoteEntity>> mNotesList;
    private Executor mExecuter = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context)
    {
        return ourInstance = new AppRepository(context);
    }

    private AppRepository(Context context) {
        mDatabase = AppDatabase.getInstance(context);
        mNotesList = getAllNotes();
    }
    public void addSmapleData() {
        mExecuter.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.notesDao().insertAll(SampleDataProvider.getSampleData());
            }
        }); }
        private LiveData<List<NoteEntity>> getAllNotes(){
        return mDatabase.notesDao().getAllNotes();
        }

    public void deleteAllData() {
        mExecuter.execute(new Runnable() {
            @Override
            public void run() {
                int itemDeleted = mDatabase.notesDao().deleteAllNotes();
                Log.d(TAG,"no of notes deleted ="+itemDeleted);
            }
        });
    }

    public NoteEntity loadNote(int noteId) {
        return mDatabase.notesDao().getNoteById(noteId);
    }

    public void insertNote(final NoteEntity noteEntity) {
        mExecuter.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.notesDao().insertNote(noteEntity);
            }
        });
    }
}
