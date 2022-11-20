package com.example.tasklist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")

data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "task_name") val taskName: String?,
    //@ColumnInfo(name = "task_nro") val taskNro: Int?,
)