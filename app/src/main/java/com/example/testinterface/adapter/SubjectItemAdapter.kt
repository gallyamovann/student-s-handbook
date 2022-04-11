package com.example.testinterface.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testinterface.model.SubjectItem
import com.example.testinterface.R


class SubjectItemAdapter(private val context: Context,
                         private val list: ArrayList<SubjectItem>,
                         private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<SubjectItemAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById<ImageView>(R.id.subjectImage)
        val titleTV: TextView = view.findViewById(R.id.subjectTitle)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.subjects_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, data.icon))
        holder.titleTV.text = data.title
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }
    }
}