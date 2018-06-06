package com.example.jokerchen.jokertestapplication

import android.content.ContentValues
import android.content.Intent
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity(), View.OnClickListener {

    val customer: Customer? = Customer("","")
    var handle: Handler? = null
    var loop: Looper? = null
    var thread: Thread? = null
    var db = AppCore.getDb()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insert.setOnClickListener(this)
        insert_1000.setOnClickListener(this)
        reset.setOnClickListener(this)
        insertw.setOnClickListener(this)
        index.setOnClickListener(this)
        query.setOnClickListener(this)
        delete.setOnClickListener(this)
        update.setOnClickListener(this)
        updateIndex.setOnClickListener(this)
        info.setOnClickListener(this)
        unIndex.setOnClickListener(this)
    }

    override fun onPostResume() {
        MyService.start(this)
        super.onPostResume()
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.reset -> reset()
            R.id.insert_1000 -> insert(1000)
            R.id.insertw -> insert(100000)
            R.id.insert -> {
                var totalTime = 0L
                for( i in 1..100 ) {
                    totalTime += insert()
                }
                Log.i("DBTime",String.format("insert time %.2f",1.0 * totalTime / 100/1000/1000))
            }
            R.id.update -> {
                var totalTime = 0L
                for( i in 1..100 ) {
                    totalTime += update()
                }
                Log.i("DBTime",String.format("update time %.2f",1.0 * totalTime / 100/1000/1000))
            }
            R.id.delete -> {
                var totalTime = 0L
                for( i in 1..100 ) {
                    totalTime += delete()
                }
                Log.i("DBTime",String.format("delete time %.2f",1.0 * totalTime / 100/1000/1000))
            }
            R.id.query -> {
                var totalTime = 0L
                for( i in 1..100 ) {
                    totalTime += query()
                }
                Log.i("DBTime",String.format("query time %.2f",1.0 * totalTime / 100/1000/1000))
            }
            R.id.index -> index()
            R.id.updateIndex -> {
                var totalTime = 0L
                for( i in 1..100 ) {
                    totalTime += updateIndex()
                }
                Log.i("DBTime",String.format("updateIndex time %.2f",1.0 * totalTime / 100/1000/1000))
            }
            R.id.info -> info()
            R.id.unIndex -> unIndex()
        }
    }

    fun index() {
        val index = " CREATE INDEX joker ON ${TABLE_NAME}(${CLOUNM_NAME_6});"
        AppCore.getDb()?.execSQL(index)
    }

    fun unIndex() {
        val index = " DROP INDEX joker;"
        AppCore.getDb()?.execSQL(index)
    }

    fun query(): Long {
        var begin = System.nanoTime()
        val random = Random(System.currentTimeMillis())
        val value = random.nextLong() % 1000
        val cur = db?.query(TABLE_NAME, arrayOf(CLOUNM_NAME_12, CLOUNM_NAME_10),"$CLOUNM_NAME_6 = $value",null,null,null,null)
        Log.i("test","${System.nanoTime() - begin}")
        val a = cur?.count
        cur?.close()
        return System.nanoTime() - begin
    }

    fun delete(): Long {
        var begin = System.nanoTime()
        try {
            val random = Random(System.currentTimeMillis())
            val value = random.nextLong() % 1000
            val contentValue = ContentValues()
            contentValue.put(CLOUNM_NAME_2,value)
            contentValue.put(CLOUNM_NAME_4,value)
            contentValue.put(CLOUNM_NAME_6,value)
            contentValue.put(CLOUNM_NAME_3,value)
            contentValue.put(CLOUNM_NAME_5,value)
            db?.delete(TABLE_NAME,"$CLOUNM_NAME_6 = $value",null)
            Log.i("joker", "inserTime is ${System.nanoTime() - begin}")

        }
        catch (e : Exception) {
            Log.i("joker",e.toString())
        }
        finally {
        }
        return System.nanoTime() - begin
    }

    fun update(): Long {
        var begin = System.nanoTime()
        try {
            val random = Random(System.currentTimeMillis())
            val value = random.nextLong() % 1000
            val contentValue = ContentValues()
            contentValue.put(CLOUNM_NAME_2,value)
            contentValue.put(CLOUNM_NAME_4,value)
            contentValue.put(CLOUNM_NAME_6,value)
            contentValue.put(CLOUNM_NAME_3,value)
            contentValue.put(CLOUNM_NAME_5,value)
            db?.update(TABLE_NAME,contentValue,"$CLOUNM_NAME_2 = $value",null)

        }
        catch (e : Exception) {
            Log.i("joker",e.toString())
        }
        finally {
        }
        return System.nanoTime() - begin
    }

    fun updateIndex(): Long {
        var begin = System.nanoTime()
        try {
            val random = Random(System.currentTimeMillis())
            val value = random.nextLong() % 1000
            val contentValue = ContentValues()
            contentValue.put(CLOUNM_NAME_2,value)
            contentValue.put(CLOUNM_NAME_4,value)
            contentValue.put(CLOUNM_NAME_6,random.nextLong() % 1000)
            contentValue.put(CLOUNM_NAME_3,value)
            contentValue.put(CLOUNM_NAME_5,value)
            db?.update(TABLE_NAME,contentValue,"$CLOUNM_NAME_6 = $value",null)

        }
        catch (e : Exception) {
            Log.i("joker",e.toString())
        }
        finally {
        }
        return System.nanoTime() - begin
    }

    fun reset() {
        this.db?.close()
        AppCore.deleteDb()
        this.db = AppCore.getDb()
    }
    inner class MyTask: AsyncTask<Long, Boolean, Boolean>() {
        override fun doInBackground(vararg params: Long?): Boolean {
            val reptedTime = params[0]

            if( reptedTime != null ) {
                var begin = System.nanoTime()
                try {
                    AppCore.getDb()?.beginTransaction()
                    for (i in 1..reptedTime) {
                        val random = Random(System.currentTimeMillis())
                        val value = random.nextLong() % 1000
                        val contentValue = ContentValues()
                        contentValue.put(CLOUNM_NAME_2,i)
                        contentValue.put(CLOUNM_NAME_4,i)
                        contentValue.put(CLOUNM_NAME_6,value)
                        contentValue.put(CLOUNM_NAME_3,i)
                        contentValue.put(CLOUNM_NAME_5,i)
                        db?.insert(TABLE_NAME,null,contentValue)
                    }
                    Log.i("joker", "inserTime is ${System.nanoTime() - begin}")

                    AppCore.getDb()?.setTransactionSuccessful()
                }
                catch (e : Exception) {
                    Log.i("joker",e.toString())
                }
                finally {
                    AppCore.getDb()?.endTransaction()
                }
            }
            return true
        }

    }

    private fun insert(): Long{
        var begin = System.nanoTime()
        val random = Random(System.currentTimeMillis())
        val value = random.nextLong() % 1000
        val contentValue = ContentValues()
        contentValue.put(CLOUNM_NAME_2,123)
        contentValue.put(CLOUNM_NAME_4,23)
        contentValue.put(CLOUNM_NAME_6,value)
        contentValue.put(CLOUNM_NAME_3,23)
        contentValue.put(CLOUNM_NAME_5,6)
        db?.insert(TABLE_NAME,null,contentValue)
        return System.nanoTime() - begin
    }
    private fun insert(long: Long) {
        val task = MyTask()
        task.execute(long)
    }

    private fun info() {
        val cursor = db?.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME",null)
        cursor?.moveToFirst()
        val count = cursor?.getLong(0)
        cursor?.close()
        Toast.makeText(this,"total count $count",Toast.LENGTH_LONG).show()
    }
}
