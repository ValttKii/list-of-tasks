package com.example.tasklist

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE isFavorite = 1")
    fun getFavorites(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("UPDATE task_table SET isFavorite = :value WHERE id = :id")
    suspend fun setFavorite(id: Int, value: Boolean)

    @Delete
    fun deleteOneTask(task: Task)

}