package com.example.notetakingapp.utils;

import com.example.notetakingapp.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider {

    private static final String SAMPLE_TEXT_1 ="Hi I am Balram";
    private static final String SAMPLE_TEXT_2 ="hi I am Bbbaaalllrrraaaammmm. Welcome to s\nmaple 2";
    private static final String SAMPLE_TEXT_3 ="hi I am Balram. Welcome to smaple 2ssssssss dddddddddd " +
            "dddddffffffffff gggggggg ewerr vbnfg dsgfh dsgreht sgerh" +
            "ergerhth dfhtrh erhgtrht";

    private  static Date getDate(int diffAmount)
    {
        GregorianCalendar calender = new GregorianCalendar();
        calender.add(Calendar.MILLISECOND,diffAmount);
        return calender.getTime();
    }

    public static List<NoteEntity> getSampleData() {
        List<NoteEntity> noteList = new ArrayList<>();
        noteList.add(new NoteEntity(1,getDate(0),SAMPLE_TEXT_1 ));
        noteList.add(new NoteEntity(2,getDate(-1),SAMPLE_TEXT_2 ));
        noteList.add(new NoteEntity(3,getDate(-2),SAMPLE_TEXT_3 ));

        return noteList;
    }
}
