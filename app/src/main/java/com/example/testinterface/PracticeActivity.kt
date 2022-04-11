package com.example.testinterface

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_practice.*
import java.io.IOException
import java.sql.SQLException

class PracticeActivity : AppCompatActivity() {
    //
    private var mDBHelper: DatabaseHelper? = null
    private var mDb: SQLiteDatabase? = null
    //
    var nameOfOption: String? = null
    var taskNumber: Int ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        val intentExtras = intent.extras
        if (intentExtras != null) {
            nameOfOption = intentExtras!!.getString("nameOfOption", "Default")
            taskNumber = intentExtras!!.getInt("taskNumber")
        }
        findViewById<TextView>(R.id.fromOption).text = nameOfOption;
        findViewById<TextView>(R.id.fromPractice).text = taskNumber.toString();
        findViewById<Button>(R.id.backToSubjects).setOnClickListener {
            val extras = Bundle()
            Log.v("Subjects", "Back-to-subjects-activity button was pressed")
            val i = Intent(this, StudyOptionsActivity::class.java)
            taskNumber?.let { it1 -> extras.putInt("taskNumber", it1) };
            i.putExtras(extras)
            startActivity(i)
        }

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
        val cursor: Cursor = mDb!!.rawQuery("SELECT * FROM theory_algebra", null)
        cursor.moveToFirst()
        textViewTask.setText(cursor.getString(1))
        cursor.close()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}