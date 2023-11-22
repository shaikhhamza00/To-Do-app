package com.hamza_dev.todoapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class demo : AppCompatActivity() {
    private lateinit var addTaskButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        addTaskButton = findViewById(R.id.addTaskButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Initialize RecyclerView and TaskAdapter
        taskAdapter = TaskAdapter(mutableListOf(), object : TaskAdapter.OnItemClickListener {
            override fun onItemClick(task: TaskAdapter.Task) {
                showTaskDetailsBottomSheet(task)
            }

            override fun onCheckBoxClick(task: TaskAdapter.Task, isChecked: Boolean) {
                // Handle checkbox click if needed
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        // Set click listener for the addTaskButton
        addTaskButton.setOnClickListener {
            showAddTaskBottomSheet()
        }
    }

}