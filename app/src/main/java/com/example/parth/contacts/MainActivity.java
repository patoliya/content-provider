package com.example.parth.contacts;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parth.contacts.database.AsyncDatabase;
import com.example.parth.contacts.database.DBAsync;
import com.example.parth.contacts.database.DatabaseDAO;

public class MainActivity extends AppCompatActivity {

    DatabaseDAO databaseDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDAO = new DatabaseDAO(this);
        String insertedAt = databaseDAO.insertFakeContact();
        Log.d("parth", insertedAt);

        DBAsync.getRegisteredContacts(this, new AsyncDatabase() {
            @Override
            public void runAfterQuering(Cursor data) {
                Log.d("parth", DatabaseUtils.dumpCursorToString(data));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
