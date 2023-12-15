package com.hfad.todolist.todo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.hfad.todolist.R
import com.hfad.todolist.data.local.TodoListDatabase
import com.hfad.todolist.data.local.models.Todo
import com.hfad.todolist.databinding.ActivityAddTodoBinding

class AddTodoActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityAddTodoBinding
    private var todoDatabase: TodoListDatabase? = null
    private var priority = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        todoDatabase = TodoListDatabase.getInstance(this)
        binding.radioGroup.setOnCheckedChangeListener(this)

        val title = intent.getStringExtra("title")
        if (title == null || title == "") {
            binding.addTodo.setOnClickListener {
                val todo = Todo(binding.titleEd.text.toString(), priority)
                todo.detail = binding.detailEd.text.toString()
                todoDatabase!!.getTodoDao().saveTodo(todo)
                finish()
            }
        } else {
            binding.addTodo.text = getString(R.string.update)
            val tId = intent.getIntExtra("tId", 0)
            binding.titleEd.setText(title)
            binding.addTodo.setOnClickListener {
                val todo = Todo(binding.titleEd.text.toString(), priority, tId)
                todo.detail = binding.detailEd.text.toString()
                todoDatabase!!.getTodoDao().updateTodo(todo)
                finish()
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.medium -> priority = 2
            R.id.high -> priority = 3
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            startActivity(Intent(this, TodoActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}