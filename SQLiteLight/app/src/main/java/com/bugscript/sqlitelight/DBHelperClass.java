package com.bugscript.sqlitelight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by syamsundark on 05/12/17.
 */

public class DBHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="personPlot.db";

    private static final int DATABASE_VERSION = 1;

    public DBHelperClass(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_PERSONS="CREATE TABLE "+ContractClass.nameClass.TABLENAME+" (" +
                ContractClass.nameClass._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ContractClass.nameClass.COLUMN_PERSON_NAME+ " TEXT NOT NULL,"+
                ContractClass.nameClass.TIME_STAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";

        db.execSQL(CREATE_TABLE_PERSONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ ContractClass.nameClass.TABLENAME);
        onCreate(db);
    }
}
