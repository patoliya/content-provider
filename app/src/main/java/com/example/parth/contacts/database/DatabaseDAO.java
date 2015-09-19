package com.example.parth.contacts.database;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;

import com.example.parth.contacts.MainActivity;

import java.util.List;

/**
 * Created by parth on 16/9/15.
 */
public class DatabaseDAO {

    Context context;

    public DatabaseDAO(Context context){
        this.context = context;
    }

    /**
     * Get all the registered contacts to show in the New Chat Activity
     */
    public String getRegisteredContacts() {
        Uri uri =ContactProvider.CONTENT_URI_CONTACT;
        String[] projection ={ContactTable.COLUMN_ID,
                ContactTable.COLUMN_NAME,
                ContactTable.COLUMN_MOB_NUMBER,
                ContactTable.COLUMN_REGISTERED};

        String selection = ContactTable.COLUMN_REGISTERED +" = ?";
        String[] selectionArgs = {"1"};
        String sortOder = ContactTable.COLUMN_NAME;

        Cursor cursor = context.getContentResolver().query(uri, projection,selection, selectionArgs, sortOder);

        return DatabaseUtils.dumpCursorToString(cursor);
//        return mContactDao.queryBuilder()
//                .where(ContactDao.Properties.Registered.eq(true))
//                .orderAsc(ContactDao.Properties.Name)
//                .list();
    }


    public String insertFakeContact(){
        Uri uri = ContactProvider.CONTENT_URI_CONTACT;



        ContentValues parthValues = new ContentValues();
        parthValues.put(ContactTable.COLUMN_NAME,"parth");
        parthValues.put(ContactTable.COLUMN_MOB_NUMBER,"7403535434");
        parthValues.put(ContactTable.COLUMN_REGISTERED,1);

        ContentValues jaydeepValues = new ContentValues();
        jaydeepValues.put(ContactTable.COLUMN_NAME,"jaydeep");
        jaydeepValues.put(ContactTable.COLUMN_MOB_NUMBER,"3534234345");
        jaydeepValues.put(ContactTable.COLUMN_REGISTERED,0);


        ContentResolver contentResolver = context.getContentResolver();
        String parthIndex = contentResolver.insert(uri, parthValues).toString();
        String jaydeepIndex = contentResolver.insert(uri,jaydeepValues).toString();

        return "parth is at "+parthIndex+" and jaydeep at "+jaydeepIndex;
    }




}
