package com.example.todoapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Task(
    @PrimaryKey var id: Long = 0,
    @Required var title: String = "",
    var completed: Boolean = false
) : RealmObject()
