package com.hfad.todolist.data.local


import android.content.Context
import androidx.room.*

//You can also check out type converters
@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, "todo_table").build()

                INSTANCE = instance
                instance
            }
        }
    }

}