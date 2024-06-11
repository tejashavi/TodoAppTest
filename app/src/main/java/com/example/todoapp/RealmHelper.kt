package com.example.todoapp

import com.example.todoapp.model.Task
import io.realm.Realm
import io.realm.RealmResults

class RealmHelper {
    private val realm: Realm = Realm.getDefaultInstance()

    fun addTask(task: Task) {
        realm.executeTransaction { r ->
            r.insert(task)
        }
    }

    fun getAllTasks(): RealmResults<Task> {
        return realm.where(Task::class.java).findAll()
    }

    fun updateTask(id: Long, title: String, completed: Boolean) {
        realm.executeTransaction { r ->
            val task = r.where(Task::class.java).equalTo("id", id).findFirst()
            task?.title = title
            task?.completed = completed
        }
    }

    fun deleteTask(id: Long) {
        realm.executeTransaction { r ->
            val task = r.where(Task::class.java).equalTo("id", id).findFirst()
            task?.deleteFromRealm()
        }
    }

    fun deleteAllTasks() {
        realm.executeTransaction { r ->
            r.where(Task::class.java).findAll().deleteAllFromRealm()
        }
    }
}
