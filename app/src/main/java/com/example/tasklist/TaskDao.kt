package com.example.tasklist

import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAll(): MutableList<Task>

    //@Query("SELECT * FROM task_table WHERE task_nro LIKE :lkm LIMIT 1")
    //suspend fun findByLkm(lkm: Int): Task

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    //  @Delete
    // suspend fun delete(task: Task)

    //@Query("DELETE FROM task_table")
    // (()) suspend fun delete
}