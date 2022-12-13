package com.example.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TaskAdapter(
    private val allTasks: List<Task>,
    private val deleteListener : (Task) -> Unit,
    private val updateListener: (Task) -> Unit,

)

    : RecyclerView.Adapter<TaskAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {



        val curTask = allTasks[position]
        holder.itemView.tvTodoTitle.text = curTask.taskName

        holder.itemView.findViewById<Button>(R.id.btnDeleteTask).setOnClickListener {
            deleteListener(curTask)
        }
        holder.itemView.findViewById<CheckBox>(R.id.cbStarCheck).setOnClickListener {
            updateListener(curTask)
        }
    }

    override fun getItemCount(): Int {

        return allTasks.size

    }
}