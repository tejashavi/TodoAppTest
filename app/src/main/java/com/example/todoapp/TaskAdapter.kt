package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.todoapp.model.Task

class TaskAdapter(private val context: Context, private val tasks: List<Task>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = tasks.size

    override fun getItem(position: Int): Task = tasks[position]

    override fun getItemId(position: Int): Long = tasks[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.item_task, parent, false)
        val task = getItem(position)

        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val completedCheckBox: CheckBox = view.findViewById(R.id.completedCheckBox)

        titleTextView.text = task.title
        completedCheckBox.isChecked = task.completed

        return view
    }
}