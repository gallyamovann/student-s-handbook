package com.example.testinterface

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_theory.*
import java.io.IOException
import java.sql.SQLException


class TheoryActivity : AppCompatActivity() {
    //
    private var mDBHelper: DatabaseHelper? = null
    private var mDb: SQLiteDatabase? = null

    //
    var nameOfOption: String? = null
    var taskNumber: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)
        val intentExtras = intent.extras
        if (intentExtras != null) {
            nameOfOption = intentExtras.getString("nameOfOption", "Default")
            taskNumber = intentExtras.getInt("taskNumber")
        }
        findViewById<TextView>(R.id.IMG_NMB).text = nameOfOption
        findViewById<TextView>(R.id.fromTheory).text = taskNumber.toString()
        findViewById<Button>(R.id.backToSubjects).setOnClickListener {
            val extras = Bundle()
            Log.v("Subjects", "Back-to-subjects-activity button was pressed")
            val i = Intent(this, StudyOptionsActivity::class.java)
            taskNumber?.let { it1 -> extras.putInt("taskNumber", it1) }
            i.putExtras(extras)
            startActivity(i)
        }

        //
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
        val cursor: Cursor = mDb!!.rawQuery("SELECT * FROM theory", null)
        var str = ""
        if (cursor != null) {
            if (cursor.moveToPosition(taskNumber.toString().toInt() - 1)) {
                for (cn in cursor.getColumnNames()) {
                    str = cursor.getString(cursor.getColumnIndex(cn))
                }
            }
            TextViewData.setText(str)
            cursor.close()
        } else {
            Log.v("LOG_TAG", "Cursor is null")
        }
//        val cursor1: Cursor =
//            mDb!!.rawQuery("SELECT * FROM theory_images WHERE _id=" + taskNumber.toString(), null)
//        val imageView = findViewById<ImageView>(R.id.imageViewTest)
//
//        // Declaring executor to parse the URL
//        val executor = Executors.newSingleThreadExecutor()
//        // Once the executor parses the URL
//        // and receives the image, handler will load it
//        // in the ImageView
//        val handler = Handler(Looper.getMainLooper())
//        // Initializing the image
//        var image: Bitmap? = null
//        // Only for Background process (can take time depending on the Internet speed)
//        executor.execute {
//            // Image URL
//            val imageURL =
//                "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png"
//            // Tries to get the image and post it in the ImageView
//            // with the help of Handler
//            try {
//                val `in` = java.net.URL(imageURL).openStream()
//                image = BitmapFactory.decodeStream(`in`)
//                // Only for making changes in UI
//                handler.post {
//                    imageView.setImageBitmap(image)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
        findViewById<Button>(R.id.imageButton).setOnClickListener {
            val extras = Bundle()
            Log.v("Subjects", "Image button was pressed")
            val i = Intent(this, ImagesActivity::class.java)
            taskNumber?.let { it1 -> extras.putInt("taskNumber", it1) }
            i.putExtras(extras)
            startActivity(i)
        }
    }

}