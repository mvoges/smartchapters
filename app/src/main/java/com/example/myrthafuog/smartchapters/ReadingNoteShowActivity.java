package com.example.myrthafuog.smartchapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ReadingNoteShowActivity extends Activity {

    TextView content;
    private ReadingNote mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_note_show);

        String noteId = getIntent().getStringExtra("noteId");
        this.mNote = ReadingNote.getReadingNote(noteId);
        //do stuff w/ mNote here

        content = (TextView)findViewById(R.id.note_text);
        content.setText(this.mNote.getText());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reading_note_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void editReadingNote(View view){
        Intent intent = new Intent(this, ReadingNoteEditActivity.class);
        intent.putExtra("noteId", mNote.getId());
        startActivity(intent);
    }

    public void deleteReadingNote(View view){
        ReadingNote.getReadingNotes().remove(mNote.getId());

        Toast.makeText(getApplicationContext(),
                "Your note " + mNote.getText() + " was deleted.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ReadingNoteListActivity.class);
        intent.putExtra("bookId", mNote.getBook().getId()); //TODO: workaround
        startActivity(intent);
    }
}
