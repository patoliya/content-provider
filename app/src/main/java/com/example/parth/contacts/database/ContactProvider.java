package com.example.parth.contacts.database;

import android.app.LoaderManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 */
public class ContactProvider extends ContentProvider {

    private DatabaseHelper databaseHelper;

    private static final String AUTHORITY = "com.example.parth.contacts";

    // this is the path to contact table
    private static final String BASE_PATH_CONTACT = "contacts";
    public static final Uri  CONTENT_URI_CONTACT = Uri.parse("content://"+AUTHORITY+"/"+BASE_PATH_CONTACT);


    private static final int CONTACT = 10;
    private static final int CONTACT_ID = 20;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(AUTHORITY,BASE_PATH_CONTACT,CONTACT);
        uriMatcher.addURI(AUTHORITY,BASE_PATH_CONTACT + "/#",CONTACT_ID);
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseHelper = new DatabaseHelper(context);
        if(databaseHelper == null){
            Log.d("parth"," databsehelper is null in onCreate");
        }else{
            Log.d("parth"," databasehelper is ok in onCreate");
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder sqLiteQueryBuilder =new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(ContactTable.TABLE_CONTACT);

        int uriCode = uriMatcher.match(uri);
        switch (uriCode){
            case CONTACT:
                break;
            case CONTACT_ID:
                sqLiteQueryBuilder.appendWhere(ContactTable.TABLE_CONTACT+" = " + uri.getLastPathSegment() );
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteQueryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if(databaseHelper==null){
            Log.d("parth"," dbhelper id null");
        }

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if(db==null){
            Log.d("parth"," db id null");
        }

        int uriCode = uriMatcher.match(uri);
        Log.d("parth"," uricode is "+uriCode);
        long id = 0;
        switch (uriCode){
            case CONTACT:
                id =db.insert(ContactTable.TABLE_CONTACT,null,values);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri "+uri );
        }

        getContext().getContentResolver().notifyChange(uri,null);

        return uri.parse(BASE_PATH_CONTACT+"/"+id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int uriType = uriMatcher.match(uri);
        SQLiteDatabase sqlDB = databaseHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case CONTACT:
                rowsDeleted = sqlDB.delete(ContactTable.TABLE_CONTACT, selection,
                        selectionArgs);
                break;
            case CONTACT_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(ContactTable.TABLE_CONTACT,
                            ContactTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(ContactTable.TABLE_CONTACT,
                            ContactTable.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        SQLiteDatabase sqlDB = databaseHelper.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case CONTACT:
                rowsUpdated = sqlDB.update(ContactTable.TABLE_CONTACT,
                        values,
                        selection,
                        selectionArgs);
                break;
            case CONTACT_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(ContactTable.TABLE_CONTACT,
                            values,
                            ContactTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(ContactTable.TABLE_CONTACT,
                            values,
                            ContactTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

}
