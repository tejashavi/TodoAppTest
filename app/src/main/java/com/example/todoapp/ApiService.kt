package com.example.todoapp

import com.example.todoapp.model.Task
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/todos")
    fun getTasks(): Call<List<Task>>

    @GET("/todos/{id}")
    fun getTask(@Path("id") id: Long): Call<Task>

    @POST("/todos")
    fun createTask(@Body task: Task): Call<Task>

    @PUT("/todos/{id}")
    fun updateTask(@Path("id") id: Long, @Body task: Task): Call<Task>

    @DELETE("/todos/{id}")
    fun deleteTask(@Path("id") id: Long): Call<Void>
}