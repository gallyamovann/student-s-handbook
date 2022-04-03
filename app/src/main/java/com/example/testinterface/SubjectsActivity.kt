package com.example.testinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubjectsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        val extras = Bundle()


        findViewById<Button>(R.id.backButton).setOnClickListener {
            Log.v("Subjects", "Back-to-main-activity button was pressed")
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
           // finish()
        }

        findViewById<Button>(R.id.buttonAlgebra).setOnClickListener {
            Log.v("Subjects", "Forward-to-Algebra-activity button was pressed")
            val i = Intent(this, StudyOptionsActivity::class.java)
            extras.putString("nameOfSubject","Алгебра");
            i.putExtras(extras)
            startActivity(i)
        }

        findViewById<Button>(R.id.buttonGeometry).setOnClickListener {
            Log.v("Subjects", "Forward-to-Geometry-activity button was pressed")
           // subj.name = "geometry"
            val i = Intent(this, StudyOptionsActivity::class.java)
            //intent.putExtra("name","geometry")
            extras.putString("nameOfSubject","Геометрия");
            i.putExtras(extras)
            startActivity(i)
        }

        findViewById<Button>(R.id.buttonMaths).setOnClickListener {
            Log.v("Subjects", "Forward-to-Maths-activity button was pressed")
           // subj.name = "maths"
            val i = Intent(this, StudyOptionsActivity::class.java)
            //intent.putExtra("name","maths")
            extras.putString("nameOfSubject","Общая математика");
            i.putExtras(extras)
            startActivity(i)
        }

        Log.v("Subjects", "Subjects activity was started")
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