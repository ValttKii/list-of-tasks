package com.example.tasklist

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAll(): LiveData<List<Task>>

    //@Query("SELECT * FROM task_table WHERE task_nro LIKE :lkm LIMIT 1")
    //suspend fun findByLkm(lkm: Int): Task

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    fun deleteOneTask(task: Task)

    //@Query("DELETE FROM task_table")
    // suspend fun deleteAll(task: Task)

    //@Query("DELETE FROM task_table WHERE Id = id")
    // suspend fun deleteTask(int: Int)
}