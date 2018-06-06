package com.example.jokerchen.jokertestapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by jokerchen on 2018/6/5.
 */
class TestDBHelp(context: Context): SQLiteOpenHelper(context,"TEST_DB",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TEST_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}