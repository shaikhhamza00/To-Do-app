package com.hamza_dev.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AddTaskFragment.OnTaskAddedListener {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(mutableListOf())
        recyclerView.adapter = taskAdapter

        // Set up the FloatingActionButton click listener
        val addTaskButton: FloatingActionButton = findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            // Show the AddTaskFragment
            showAddTaskFragment()
        }
    }

    // Function to show the AddTaskFragment
    private fun showAddTaskFragment() {
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.setOnTaskAddedListener(this) // Pass the MainActivity as the listener
        addTaskFragment.show(supportFragmentManager, addTaskFragment.tag)
    }

    // Implementation of the OnTaskAddedListener interface
    override fun onTaskAdded(title: String, description: String, endDate: String) {
        // Handle the new task addition here
        // For now, let's add the task to the RecyclerView adapter
        taskAdapter.addNewTask(title, description)

        // You may also want to update your data source or perform other actions
    }
}
