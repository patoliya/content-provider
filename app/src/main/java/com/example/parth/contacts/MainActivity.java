package com.example.parth.contacts;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parth.contacts.database.ContactProvider;
import com.example.parth.contacts.database.ContactTable;
import com.example.parth.contacts.database.DatabaseDAO;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    DatabaseDAO databaseDAO;

    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;
    private static final int LOADER_ID = 1;

    String[] projection ={ContactTable.COLUMN_ID,
            ContactTable.COLUMN_NAME,
            ContactTable.COLUMN_MOB_NUMBER,
            ContactTable.COLUMN_REGISTERED};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCallbacks = this;

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID,null,mCallbacks);



        databaseDAO = new DatabaseDAO(this);
        String insertedAt = databaseDAO.insertFakeContact();
        Log.d("parth",insertedAt);

//        String registeredContacts = databaseDAO.getRegisteredContacts();
//        Log.d("parth",registeredContacts);

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





    // methods of Loader manager
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MainActivity.this,ContactProvider.CONTENT_URI_CONTACT,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()){
            case LOADER_ID:
                    Log.d("parth", DatabaseUtils.dumpCursorToString(data));
                break;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d("parth","  === Loader got reset by loader manager ");
    }
}
