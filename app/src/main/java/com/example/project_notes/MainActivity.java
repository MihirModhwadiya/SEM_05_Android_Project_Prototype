package com.example.project_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_NAME = "NotePref3";
    public static final String KEY = "NoteCount3";
    public LinearLayout noteContainer;
//    public NotesHover sh = new NotesHover();
    public List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton new_note = findViewById(R.id.new_note);

        noteContainer = findViewById(R.id.noteContainer);

        new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotesHover.class);
                ArrayList<Note> noteListArrayList = new ArrayList<>(noteList);
                intent.putParcelableArrayListExtra("noteList", noteListArrayList);
                startActivity(intent);
            }
        });

        EditText search = findViewById(R.id.searchEditText);
        String searchTXT = search.getText().toString();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchTXT = search.getText().toString();
//                dispayNotes();
            }
        });

        loadNotesFromPreferences();
        dispayNotes();
//        Toast.makeText(this, ""+noteList.size(), Toast.LENGTH_SHORT).show();
    }
    public void createNoteView(final Note note) {
        View noteview = getLayoutInflater().inflate(R.layout.note_item, null);
        TextView title = noteview.findViewById(R.id.title_TextView);
        TextView desc = noteview.findViewById(R.id.desc_TextView);

        title.setText(note.getTitle());
        desc.setText(note.getDesc());

        noteContainer.addView(noteview);
    }
    public void dispayNotes() {
        for (Note note : noteList) {
                createNoteView(note);
        }
    }

    public void loadNotesFromPreferences() {
        SharedPreferences spp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        int noteCount = spp.getInt(KEY, 0);

        for (int i = 0; i < noteCount; i++) {

            String title = spp.getString("note_title" + i, "");
            String desc = spp.getString("note_desc" + i, "");

            Note note = new Note();
            note.setTitle(title);
            note.setDesc(desc);

            noteList.add(note);
        }
    }
}