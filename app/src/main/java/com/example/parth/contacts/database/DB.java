package com.example.parth.contacts.database;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;

/**
 *  this provides async data from database, It also has
 */
public class DB {

    private static final int LOADER_ID = 1;

    public static void getRegisteredContacts(final Activity activity, final AsyncDatabase asyncDatabase){

        final QueryArgument queryArgument = QueryMaker.getQueryForRegisteredContacts();

        LoaderManager loaderManager = activity.getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new CursorLoader(activity,queryArgument.uri,queryArgument.projection
                ,queryArgument.selection,queryArgument.selectionArgs,queryArgument.sortOrder);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                switch (loader.getId()){
                    case LOADER_ID:
                        Log.d("parth", DatabaseUtils.dumpCursorToString(data));
                        asyncDatabase.runAfterQuering(data);
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                Log.d("parth","  === Loader got reset by loader manager ");
            }
        });

    }

}
