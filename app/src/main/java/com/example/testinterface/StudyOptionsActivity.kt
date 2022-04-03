package com.example.testinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudyOptionsActivity : AppCompatActivity() {
    var nameOfSubject: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_options)
        val intentExtras = intent.extras
        if (intentExtras != null) {
            nameOfSubject = intentExtras!!.getString("nameOfSubject", "Default")
        }
        val extras = Bundle()
        findViewById<Button>(R.id.TheoryButton).setOnClickListener {
            Log.v("Subjects", "Forward-to-Theory-activity button was pressed")
            extras.putString("nameOfSubject", nameOfSubject);
            extras.putString("nameOfOption", "Theory")
            val i = Intent(this, TheoryActivity::class.java)
            i.putExtras(extras)
            startActivity(i)
        }
        findViewById<Button>(R.id.PracticeButton).setOnClickListener {
            Log.v("Subjects", "Forward-to-Practice-activity button was pressed")
            extras.putString("nameOfSubject", nameOfSubject);
            extras.putString("nameOfOption", "Practice")
            val i = Intent(this, PracticeActivity::class.java)
            i.putExtras(extras)
            startActivity(i)
        }
        findViewById<Button>(R.id.backToSubjects).setOnClickListener {
            Log.v("Subjects", "Back-to-subjects-activity button was pressed")
            val i = Intent(this, SubjectsActivity::class.java)
            startActivity(i)
            //finish()
        }
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