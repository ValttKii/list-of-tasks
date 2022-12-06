package com.example.tasklist

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAll()
    val allFavorites: LiveData<List<Task>> = taskDao.getFavorites()

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

    @Suppress( "RedundantSuspendModifier")
    @WorkerThread
    suspend fun makeFavoriteActive(t: Task){
        t.id?.let { taskDao.setFavorite(it, !t.isFavorite!!) }
    }

}