package com.example.testinterface

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_practice.*
import kotlinx.android.synthetic.main.activity_theory.*
import java.io.IOException
import java.sql.SQLException
import java.util.concurrent.Executors


class ImagesActivity : AppCompatActivity() {
    private var mDBHelper: DatabaseHelper? = null
    private var mDb: SQLiteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        //
        var nameOfOption: String? = null
        var taskNumber: Int? = null
        val intentExtras = intent.extras
        if (intentExtras != null) {
            nameOfOption = intentExtras.getString("nameOfOption", "Default")
            taskNumber = intentExtras.getInt("taskNumber")
        }
        findViewById<Button>(R.id.backToTheory).setOnClickListener {
            val extras = Bundle()
            Log.v("Subjects", "Image button was pressed")
            val i = Intent(this, TheoryActivity::class.java)
            taskNumber?.let { it1 -> extras.putInt("taskNumber", it1) }
            i.putExtras(extras)
            startActivity(i)
        }
        var num = 0
        val imageView = findViewById<ImageView>(R.id.imageTheory)
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null
        mDBHelper = DatabaseHelper(this)

        try {
            mDBHelper!!.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        mDb = try {
            mDBHelper!!.writableDatabase
        } catch (mSQLException: SQLException) {
            throw mSQLException
        }
        val cursor: Cursor =
            mDb!!.rawQuery("SELECT * FROM theory_images WHERE _id=" + taskNumber.toString(), null)
        var str = ""
        cursor.moveToFirst()
        val list: ArrayList<String> = ArrayList()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                var str: String
                do {
                    str = ""
                    for (cn in cursor.columnNames) {
                        if (cn == "data") {
                            str = cursor.getString(cursor.getColumnIndex(cn))
                            list.add(str)
                        }
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
        } else {
            Log.v("LOG_TAG", "Cursor is null")
        }
        if(num>list.size){
            num=-1
        }
        if (list.size != 0) {
            executor.execute {
                var str = list.get(0)
                val imageURL = str
                try {
                    val `in` = java.net.URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    // Only for making changes in UI
                    handler.post {
                        imageView.setImageBitmap(image)
                    }
                    findViewById<TextView>(R.id.IMG_NMB).text = "Рисунок №" + (num + 1)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            findViewById<Button>(R.id.nextImage).setOnClickListener {
                executor.execute {
                    num++
                    if(num>=list.size){
                        num=0
                    }
                    var str = list.get(num)
                    val imageURL = str
                    try {
                        val `in` = java.net.URL(imageURL).openStream()
                        image = BitmapFactory.decodeStream(`in`)
                        handler.post {
                            imageView.setImageBitmap(image)
                        }
                        findViewById<TextView>(R.id.IMG_NMB).text = "Рисунок №" + (num + 1)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } else {
            findViewById<TextView>(R.id.IMG_NMB).text = "Изображений нет"
        }
    }
}
