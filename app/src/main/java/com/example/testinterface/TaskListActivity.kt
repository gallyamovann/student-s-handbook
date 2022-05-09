package com.example.testinterface

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testinterface.adapter.CellClickListener
import com.example.testinterface.adapter.SubjectItemAdapter
import com.example.testinterface.model.SubjectItem

class TaskListActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        val extras = Bundle()

        // Displaying list of tasks
        val recyclerView: RecyclerView = findViewById(R.id.subjectRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val list : ArrayList<SubjectItem> = fetchList();
        recyclerView.adapter = SubjectItemAdapter(this, list,this)

        //if some subject was clicked, go to next activity
        findViewById<Button>(R.id.backButton).setOnClickListener {
            Log.v("Subjects", "Back button was pressed")
            val i = Intent(this, ChooseEgeActivity::class.java)
            startActivity(i)
           // finish()
        }


        Log.v("Subjects", "Subjects activity was started")
    }

    // function to create a list of tasks
    private fun fetchList(): ArrayList<SubjectItem> {
        val list = arrayListOf<SubjectItem>()

        for (i in 1..11) {
            val model = SubjectItem(
                i,
                R.drawable.dog2,
                "$i")
            list.add(model)
        }
        return list
    }

    //function to do smth when subject was clicked
    override fun onCellClickListener(data: SubjectItem) {
        //Toast.makeText(this,"Cell clicked", Toast.LENGTH_SHORT).show()
        val extras = Bundle()
       // Log.v(, )
        val i = Intent(this, StudyOptionsActivity::class.java)
        // передача информации о номере задачи
        extras.putInt("taskNumber",data.id)
        i.putExtras(extras)
        startActivity(i)

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