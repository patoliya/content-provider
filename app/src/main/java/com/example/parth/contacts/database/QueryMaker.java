package com.example.parth.contacts.database;

import android.net.Uri;

/**
 * Created by parth on 19/9/15.
 */
public class QueryMaker {

    public static QueryArgument getQueryForRegisteredContacts(){

        QueryArgument queryArgument = new QueryArgument() ;
        queryArgument.uri = ContactProvider.CONTENT_URI_CONTACT;
        queryArgument.projection = new String[] {ContactTable.COLUMN_ID,
                ContactTable.COLUMN_NAME,
                ContactTable.COLUMN_MOB_NUMBER,
                ContactTable.COLUMN_REGISTERED};
        queryArgument.selection = ContactTable.COLUMN_REGISTERED + " = ? ";
        queryArgument.selectionArgs = new String[] {"1"};
        queryArgument.sortOrder = ContactTable.COLUMN_NAME;

        return queryArgument;

    }



}
