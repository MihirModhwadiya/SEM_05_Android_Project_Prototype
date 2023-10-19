package com.example.project_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.Key;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class NotesHover extends AppCompatActivity {

    public static final String PREF_NAME = "NotePref3";
    public static final String KEY = "NoteCount3";
    public LinearLayout noteContainer;
    public List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_hover);

        FloatingActionButton new_note = findViewById(R.id.new_note);

        ArrayList<Note> receivedNoteList = getIntent().getParcelableArrayListExtra("noteList");
        if (receivedNoteList != null) {
            noteList.addAll(receivedNoteList);
        }
//        Toast.makeText(this, ""+noteList.size(), Toast.LENGTH_SHORT).show();

        View noteview = getLayoutInflater().inflate(R.layout.activity_main, null);
        noteContainer = noteview.findViewById(R.id.noteContainer);

        new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savenotes();
            }
        });
//        Toast.makeText(this, "" + noteList.size(), Toast.LENGTH_SHORT).show();
    }

    public void savenotes() {
        EditText title = findViewById(R.id.Title_EditText);
        EditText desc = findViewById(R.id.Desc_EditText);

        String title_string = title.getText().toString();
        String desc_string = desc.getText().toString();

        if (!title_string.isEmpty() && !desc_string.isEmpty()) {

            Note nh = new Note();

            nh.setTitle(title_string);
            nh.setDesc(desc_string);

            noteList.add(nh);

            savenotestoPref();
            createNoteView(nh);

            Intent intent = new Intent(NotesHover.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void createNoteView(final Note note) {
        View noteview = getLayoutInflater().inflate(R.layout.note_item, null);
        TextView title = noteview.findViewById(R.id.title_TextView);
        TextView desc = noteview.findViewById(R.id.desc_TextView);

        title.setText(note.getTitle());
        desc.setText(note.getDesc());
    }

    public void savenotestoPref() {

        SharedPreferences sh1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sh1.edit();

        editor.putInt(KEY, noteList.size());
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);

            editor.putString("note_title" + i, note.getTitle());
            editor.putString("note_desc" + i, note.getDesc());
        }
        editor.apply();
    }
}