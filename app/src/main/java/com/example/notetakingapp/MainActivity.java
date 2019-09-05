package com.example.notetakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.notetakingapp.database.NoteEntity;
import com.example.notetakingapp.model.NotesAdapter;
import com.example.notetakingapp.utils.SampleDataProvider;
import com.example.notetakingapp.viewmodels.ListActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<NoteEntity> mNotesList =new ArrayList<>();
    private ListActivityViewModel mViewModel;
    NotesAdapter mNotesAdapter;

    @BindView(R.id.notes_recycleview)
    RecyclerView mRecycleView;
    @OnClick(R.id.fab_add_note)
    void onFabClicked()
    {
        Intent intent =new Intent(MainActivity.this, EditorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewModel();

        ButterKnife.bind(this);
        initRecycleView();
    }

    private void initViewModel() {
        Observer<List<NoteEntity>> notesObserver = new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                mNotesList.clear();
                mNotesList.addAll(noteEntities);
                if(mNotesAdapter==null){
                    mNotesAdapter =new NotesAdapter(MainActivity.this,mNotesList);
                    mRecycleView.setAdapter(mNotesAdapter);
                }
                else {
                 mNotesAdapter.notifyDataSetChanged();
                }
            }
        };
        mViewModel = ViewModelProviders.of(this).get(ListActivityViewModel.class);
        mViewModel.mNotesList.observe(MainActivity.this,notesObserver);
    }
    private  void initRecycleView()
{
    mRecycleView.hasFixedSize();
    LinearLayoutManager layoutManager= new LinearLayoutManager(this);
    mRecycleView.setLayoutManager(layoutManager);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.add_sample_data :
            {
                addSampleData();
                return  true;
            }
            case R.id.delete_all_data:
            {
                deleteAllData();
                return  true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllData() {
        mViewModel.deleteAllData();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
