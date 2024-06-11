package com.example.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.model.Task

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTaskActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var realmHelper: RealmHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        titleEditText = findViewById(R.id.titleEditText)
        saveButton = findViewById(R.id.saveButton)
        realmHelper = RealmHelper()

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            if (title.isNotEmpty()) {
                val task = Task()
                task.id = System.currentTimeMillis()
                task.title = title
                task.completed = false
                realmHelper.addTask(task)
                val call = RetrofitClient.instance.createTask(task)
                call.enqueue(object : Callback<Task> {
                    override fun onResponse(call: Call<Task>, response: Response<Task>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AddTaskActivity, "Task created successfully", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@AddTaskActivity, "Failed to create task in backend", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Task>, t: Throwable) {
                        Toast.makeText(this@AddTaskActivity, "Network error. Task saved locally", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
            } else {
                Toast.makeText(this, "Task title cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}