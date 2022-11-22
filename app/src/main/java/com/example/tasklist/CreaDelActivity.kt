package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.databinding.CreadelTodoBinding
import kotlinx.android.synthetic.main.creadel_todo.*
import kotlinx.android.synthetic.main.item_todo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreaDelActivity : AppCompatActivity() {
    private lateinit var binding: CreadelTodoBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var items: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreadelTodoBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModel::class.java)

        viewModel.allTasks.observe(this) {
            items = it
            binding.rvTodoItems.adapter = TodoAdapter(items, ::deleteTask)
        }
        setContentView(binding.root)
        binding.rvTodoItems.apply {
            layoutManager = LinearLayoutManager(this@CreaDelActivity)
        }

        binding.btnAddTodo.setOnClickListener {
            writeData()
        }
    }

    private fun writeData() {
        val taskName = binding.etTodoTitle.text.toString()

        if (taskName.isNotEmpty()) {
            val task = Task(
                null, taskName
            )
            viewModel.insertTask(task)
            binding.etTodoTitle.text.clear()

            Toast.makeText(this@CreaDelActivity, "OK", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@CreaDelActivity, "NOT OK", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTask(task: Task) {
        viewModel.deleteTask(task)
    }

}