package com.hfad.todolist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
        @PrimaryKey(autoGenerate = true) val id: Int?,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "note") val note: String?,
        @ColumnInfo(name = "date") val date: String,
        @ColumnInfo(name = "priority") val priority: Int
): java.io.Serializable