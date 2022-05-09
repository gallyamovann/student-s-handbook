package com.example.testinterface

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
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
    //
    var isCheckNow: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        val intentExtras = intent.extras
        if (intentExtras != null) {
            nameOfOption = intentExtras.getString("nameOfOption", "Default")
            taskNumber = intentExtras.getInt("taskNumber")
        }
        findViewById<TextView>(R.id.fromOption).text = nameOfOption
        findViewById<TextView>(R.id.fromPractice).text = taskNumber.toString()
        findViewById<Button>(R.id.backToSubjects).setOnClickListener {
            val extras = Bundle()
            Log.v("Subjects", "Back-to-subjects-activity button was pressed")
            val i = Intent(this, StudyOptionsActivity::class.java)
            taskNumber?.let { it1 -> extras.putInt("taskNumber", it1) }
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

        val cursor: Cursor = mDb!!.rawQuery("SELECT * FROM practice WHERE taskNumber=" + taskNumber.toString(), null)

        //val cursor: Cursor = mDb!!.rawQuery("SELECT * FROM practice", null)
        cursor.moveToFirst()
        textViewPractice.setText(cursor.getString(1))

        //надо бы в отдельную функцию вынести
        findViewById<Button>(R.id.CheckAnswerOrMoveNext).setOnClickListener {
            if(isCheckNow) {
                if (findViewById<TextInputEditText>(R.id.textInputPractice).text.toString() == cursor.getString(
                        cursor.getColumnIndex("key")
                    )
                ) {
                    Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show()
                    findViewById<Button>(R.id.CheckAnswerOrMoveNext).setBackgroundColor(resources.getColor(R.color.correct_answer_color))
                    findViewById<Button>(R.id.CheckAnswerOrMoveNext).text = "далее"
                    isCheckNow = false

                } else {
                    Toast.makeText(this, "INCORRECT", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                //TODO если дошли до конца то куда-то возвращаться?
                cursor.moveToNext()
                findViewById<Button>(R.id.CheckAnswerOrMoveNext).setBackgroundColor(resources.getColor(R.color.purple_500))
                findViewById<Button>(R.id.CheckAnswerOrMoveNext).text = "➜"
                textViewPractice.setText(cursor.getString(1))
                isCheckNow = true
            }
        }


        //cursor.close()
    }

}


