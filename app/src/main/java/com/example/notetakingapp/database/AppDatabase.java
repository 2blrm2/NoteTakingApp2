package com.example.notetakingapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {NoteEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notesdatabase.db";
    public static volatile AppDatabase instance;
    private static final Object LOCK = new Object();
    public abstract NotesDao notesDao();

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,DATABASE_NAME).build();
                }
            }
    }
        return instance;
}
}
