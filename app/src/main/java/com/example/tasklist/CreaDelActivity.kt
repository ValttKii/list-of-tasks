package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.databinding.CreadelTodoBinding
import kotlinx.android.synthetic.main.creadel_todo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreaDelActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding : CreadelTodoBinding
    private lateinit var appDb : AppDatabas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreadelTodoBinding.inflate(layoutInflater)

        appDb = AppDatabas.getDatabase(this)

        setContentView(binding.root)

        val allTasks = appDb.taskDao().getAll()

        rvTodoItems.apply {
            layoutManager = LinearLayoutManager(this@CreaDelActivity)
            adapter = TodoAdapter(allTasks)
        }


        binding.btnAddTodo.setOnClickListener {
            writeData()
        }


        /*todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

       btnAddTodo.setOnClickListener{

            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()

            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }*/
    }

    private fun writeData(){
        val taskName = binding.etTodoTitle.text.toString()

        if (taskName.isNotEmpty()){
            val task = Task(
                null, taskName
            )
            GlobalScope.launch(Dispatchers.IO){
                appDb.taskDao().insert(task)
            }
            binding.etTodoTitle.text.clear()

            Toast.makeText(this@CreaDelActivity, "OK", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this@CreaDelActivity, "NOT OK", Toast.LENGTH_SHORT).show()
        }
    }
}