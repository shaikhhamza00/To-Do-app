package com.hamza_dev.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    // Task data class
    data class Task(val title: String, val description: String, var isCompleted: Boolean)

    // ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTask = tasks[position]

        holder.titleTextView.text = currentTask.title
        holder.descriptionTextView.text = currentTask.description
        holder.checkBox.isChecked = currentTask.isCompleted

        // Handle checkbox click
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            // Update the completion status of the task
            currentTask.isCompleted = isChecked
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    // Function to add a new task with default completion status as false
    fun addNewTask(title: String, description: String) {
        val newTask = Task(title, description, isCompleted = false)
        addTask(newTask)
    }

    // Function to add a task
    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }


}
