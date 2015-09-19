package com.example.parth.contacts.database;

import android.content.Loader;
import android.database.Cursor;

/**
 *   This provides facilities to activity to do some task after running query
 *   example -  update Ui components with fetched data
 */
public interface AsyncDatabase {

    public void runAfterQuering(Cursor data);
}
