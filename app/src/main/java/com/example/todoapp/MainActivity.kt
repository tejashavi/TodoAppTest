package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.widget.Button
import android.widget.ListView

import com.example.todoapp.model.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private lateinit var realmHelper: RealmHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.taskListView)
        addButton = findViewById(R.id.addTaskButton)
        realmHelper = RealmHelper()

        addButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        fetchTasks()
    }

    private fun fetchTasks() {
        val call = RetrofitClient.instance.getTasks()
        call.enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                if (response.isSuccessful) {
                    val tasks = response.body()
                    tasks?.let {
                        // Clear the existing tasks in Realm before adding new ones
                        realmHelper.deleteAllTasks()
                        it.forEach { task ->
                            realmHelper.addTask(task)
                        }
                        updateListView()
                    }
                }
            }
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                updateListView()
            }
        })
    }

    private fun updateListView() {
        val tasks = realmHelper.getAllTasks()
        val adapter = TaskAdapter(this, tasks)
        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateListView()
    }
}