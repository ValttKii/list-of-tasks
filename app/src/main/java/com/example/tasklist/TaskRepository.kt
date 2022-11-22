package com.example.tasklist

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAll()

    @Suppress( "RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(t: Task){
        taskDao.insert(t)
    }

    @Suppress( "RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(t: Task){
        taskDao.deleteOneTask(t)
    }

}