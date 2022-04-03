package com.example.testinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChooseEgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_ege)

        findViewById<Button>(R.id.toProfileMath).setOnClickListener {
            Log.v(" ChooseEgeActivity", "toProfileMath button was pressed")
            val i = Intent(this, SubjectsActivity::class.java)
            startActivity(i)
        }

        findViewById<Button>(R.id.toBasicMath).setOnClickListener {
            Log.v(" ChooseEgeActivity", "toBasicMath button was pressed")
            val i = Intent(this, ComingSoonActivity::class.java)
            startActivity(i)
        }

        findViewById<Button>(R.id.toRus).setOnClickListener {
            Log.v(" ChooseEgeActivity", "toRus button was pressed")
            val i = Intent(this, ComingSoonActivity::class.java)
            startActivity(i)
        }

        findViewById<Button>(R.id.toMain).setOnClickListener {
            Log.v(" ChooseEgeActivity", "toMain button was pressed")
            val i = Intent(this, ComingSoonActivity::class.java)
            startActivity(i)
        }
        Log.v(" ChooseEgeActivity", "Activity was started")
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