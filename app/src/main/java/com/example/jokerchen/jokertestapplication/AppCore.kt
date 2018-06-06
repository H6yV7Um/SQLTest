package com.example.jokerchen.jokertestapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Created by jokerchen on 2018/6/5.
 */
object AppCore{

    lateinit var testDb : TestDBHelp
    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
        testDb = TestDBHelp(context)
    }
    fun getDb(): SQLiteDatabase? {
        return testDb.writableDatabase
    }

    fun deleteDb() {
        context.deleteDatabase("TEST_DB")
        testDb = TestDBHelp(context)
    }

}