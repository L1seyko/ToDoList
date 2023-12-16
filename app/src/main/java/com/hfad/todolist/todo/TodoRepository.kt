package com.hfad.todolist.todo

import androidx.lifecycle.LiveData
import com.hfad.todolist.data.local.TodoDao
import com.hfad.todolist.data.local.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()
    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

    suspend fun update(todo: Todo){
        todoDao.update(todo.id, todo.title, todo.note)
    }
}