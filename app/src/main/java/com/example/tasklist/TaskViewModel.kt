package com.example.tasklist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (application: Application): AndroidViewModel(application){
    val allTasks: LiveData<List<Task>>
    val allFavorites: LiveData<List<Task>>

    private val repository: TaskRepository

    init {
        val dao = AppDatabas.getDatabase(application).taskDao()
        repository = TaskRepository(dao)
        allTasks = repository.allTasks
        allFavorites = repository.allFavorites
    }
    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.makeFavoriteActive(task)
    }

}