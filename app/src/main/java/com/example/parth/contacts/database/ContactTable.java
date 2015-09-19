package com.example.parth.contacts.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by parth on 15/9/15.
 */
public class ContactTable {

    //Database table
    public static final String TABLE_CONTACT = "todo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOB_NUMBER = "MOB_NUMBER";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_IN_ADDRESS_BOOK = "IN_ADDRESS_BOOK";
    public static final String COLUMN_REGISTERED = "REGISTERED";
    public static final String COLUMN_ONLINE = "ONLINE";
    public static final String COLUMN_HAS_DP = "HAS_DP";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_STATUS_DATE = "STATUS_DATE";
    public static final String COLUMN_UNREAD_MESSAGES = "UNREAD_MESSAGES";
    public static final String COLUMN_LAST_MSG_DATE = "LAST_MSG_DATE";
    public static final String COLUMN_LAST_MSG = "LAST_MSG";
    public static final String COLUMN_LAST_SEEN = "LAST_SEEN";
    public static final String COLUMN_LAST_ONLINE_NOTF_TIME = "LAST_ONLINE_NOTF_TIME";
    public static final String COLUMN_WILL_GET_ONLINE_NOTIF = "WILL_GET_ONLINE_NOTIF";



    private static final String DATABASE_CREATE = "create table "
            + TABLE_CONTACT
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MOB_NUMBER + " text not null, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_IN_ADDRESS_BOOK + " integer, "
            + COLUMN_REGISTERED + " integer, "
            + COLUMN_ONLINE + " integer, "
            + COLUMN_HAS_DP + " integer, "
            + COLUMN_STATUS + " integer, "
            + COLUMN_STATUS_DATE + " integer, "
            + COLUMN_UNREAD_MESSAGES + " integer, "
            + COLUMN_LAST_MSG_DATE + " integer, "
            + COLUMN_LAST_MSG + " text, "
            + COLUMN_LAST_SEEN + " integer, "
            + COLUMN_LAST_ONLINE_NOTF_TIME + " integer, "
            + COLUMN_WILL_GET_ONLINE_NOTIF + " integer "
            + ");";

    public static void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(ContactTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        onCreate(database);
    }


}
