package com.example.notetakingapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.notetakingapp.database.AppRepository;
import com.example.notetakingapp.database.NoteEntity;
import com.example.notetakingapp.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotesList;
    private AppRepository mRepository;

    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository= AppRepository.getInstance(application.getApplicationContext());
        mNotesList= mRepository.mNotesList;
    }

    public void addSampleData() {
         mRepository.addSmapleData();
    }

    public void deleteAllData() {
        mRepository.deleteAllData();
    }
}
