package com.example.testinterface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class TheoryActivity : AppCompatActivity() {
    var nameOfOption: String? = null
    var nameOfSubject: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)
        val intentExtras = intent.extras
        if (intentExtras != null) {
            nameOfOption = intentExtras!!.getString("nameOfOption", "Default")
            nameOfSubject = intentExtras!!.getString("nameOfSubject", "Default")
        }
        findViewById<TextView>(R.id.fromOption).text = nameOfOption;
        findViewById<TextView>(R.id.fromTheory).text = nameOfSubject;
        findViewById<Button>(R.id.backToSubjects).setOnClickListener {
            val extras = Bundle()
            Log.v("Subjects", "Back-to-subjects-activity button was pressed")
            val i = Intent(this, StudyOptionsActivity::class.java)
            extras.putString("nameOfSubject", nameOfSubject);
            i.putExtras(extras)
            startActivity(i)
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