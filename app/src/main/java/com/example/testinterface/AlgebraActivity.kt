package com.example.testinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AlgebraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algebra)

        findViewById<Button>(R.id.AlgebraBackButton).setOnClickListener {
            Log.v("Subjects", "Back-to-subjects-activity button was pressed")
            val i = Intent(this, SubjectsActivity::class.java)
            startActivity(i)
        }

        Log.v("Algebra", "Algebra activity was started")
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