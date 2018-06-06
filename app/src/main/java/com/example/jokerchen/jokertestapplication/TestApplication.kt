package com.example.jokerchen.jokertestapplication

import android.app.Application
import android.database.sqlite.SQLiteDatabase

/**
 * Created by jokerchen on 2018/6/5.
 */

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCore.init(applicationContext)
    }
}
