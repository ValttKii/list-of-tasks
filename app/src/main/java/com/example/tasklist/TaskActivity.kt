package com.example.tasklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.databinding.CreadelTodoBinding
import com.example.tasklist.databinding.ItemTodoBinding

class TaskActivity : AppCompatActivity() {

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
            binding.rvTodoItems.adapter = TaskAdapter(items, ::deleteTask, ::changeValue)
        }
        setContentView(binding.root)
        binding.rvTodoItems.apply {
            layoutManager = LinearLayoutManager(this@TaskActivity)
        }

        binding.btnAddTodo.setOnClickListener {
            writeData()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_opt_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {

        when(item.itemId){
            R.id.action_favorite ->
                startActivity(Intent(this, FavoriteActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }

    private fun writeData() {
        val taskName = binding.etTodoTitle.text.toString()

        if (taskName.isNotEmpty()) {
            val task = Task(
                null, taskName, false
            )
            viewModel.insertTask(task)
            binding.etTodoTitle.text.clear()

            Toast.makeText(this@TaskActivity, "OK", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@TaskActivity, "NOT OK", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTask(task: Task) {
        viewModel.deleteTask(task)
    }
    private fun changeValue(task: Task){

        viewModel.updateTask(task)
    }

}