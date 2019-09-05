package com.example.notetakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.notetakingapp.database.NoteEntity;
import com.example.notetakingapp.utils.Constants;
import com.example.notetakingapp.viewmodels.EditorViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {

    EditorViewModel mViewModel;
    @BindView(R.id.edit_note_text)
    TextView mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

      initViewModel();
    }

    private void initViewModel() {
        Observer<NoteEntity> noteObserver = new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {

              //  mEditText.setText(Objects.requireNonNull(noteEntity).getText());
                if (noteEntity != null) {
                    mEditText.setText(noteEntity.getText());
                }
            }
        };
        mViewModel = ViewModelProviders.of(this).get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this,noteObserver);

        Bundle bundle = getIntent().getExtras();
        if (bundle==null)
        {
            setTitle("New Note..");
        }
        else{
            setTitle("Editor Note..");
            int noteId= bundle.getInt(Constants.NOTE_ID_KEY);
            mViewModel.loadNote(noteId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            saveAndExit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveAndExit();
    }

    private void saveAndExit() {
        mViewModel.saveAndExit(mEditText.getText().toString());
    }
}
