package com.example.parth.contacts.database;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by parth on 20/9/15.
 */
public class DBSync {

    public static Cursor getRegisteredContacts(Context context){

        QueryArgument queryArgument = QueryMaker.getQueryForRegisteredContacts();

        Cursor data = context.getContentResolver().query(queryArgument.uri,queryArgument.projection,
                queryArgument.selection,queryArgument.selectionArgs,queryArgument.sortOrder);

        return data;
    }

}
