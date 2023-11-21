package com.hamza_dev.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        init {
            // Set click listener for the item view
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val task = tasks[position]
                    itemClickListener.onItemClick(task)
                }
            }

            // Set click listener for the checkbox
            checkBox.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val task = tasks[position]
                    itemClickListener.onCheckBoxClick(task, checkBox.isChecked)
                }
            }
        }

        fun bind(task: Task) {
            titleTextView.text = task.title
            checkBox.isChecked = task.isCompleted
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    // Other methods for adding, updating, or removing tasks
    // ...

    // Function to add a new task with default completion status as false
    fun addNewTask(title: String, description: String) {
        // You may need to provide a default value for isCompleted
        val newTask = Task(title = title, description = description, dueDate = "Due Date", isCompleted = false)
        addTask(newTask)
    }

    // Function to add a task
    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    // Function to update a task
    fun updateTask(task: Task) {
        val position = tasks.indexOfFirst { it.title == task.title }
        if (position != -1) {
            tasks[position] = task
            notifyItemChanged(position)
        }
    }

    // Function to remove a task
    fun removeTask(task: Task) {
        val position = tasks.indexOfFirst { it.title == task.title }
        if (position != -1) {
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    data class Task(
        val title: String,
        val description: String,
        val dueDate: String,
        var isCompleted: Boolean
    )
}
