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

class MainActivity : AppCompatActivity() {

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

    private fun showAddTaskBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.fragment_add_task, null)

        val titleEditText: EditText = view.findViewById(R.id.titleEditText)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionEditText)
        val addButton: FloatingActionButton = view.findViewById(R.id.saveButton)

        // Set click listener for the addButton
        addButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            if (title.isNotEmpty()) {
                // Replace "Due Date" with your logic to get the due date
                val dueDate = "Due Date"

                // Add the new task to the list
                val newTask = TaskAdapter.Task(title, description, dueDate, isCompleted = false)
                taskAdapter.addTask(newTask)

                // Dismiss the bottom sheet
                bottomSheetDialog.dismiss()
            }
        }

        // Set text change listener for the titleEditText
        titleEditText.addTextChangedListener {
            // Enable or disable the addButton based on the titleEditText's content
            addButton.isEnabled = !it.isNullOrBlank()
        }

        // Set the content view of the bottom sheet
        bottomSheetDialog.setContentView(view)

        // Show the bottom sheet
        bottomSheetDialog.show()
    }

    private fun showTaskDetailsBottomSheet(task: TaskAdapter.Task) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_task_details, null)

        // Find views in the bottom sheet layout
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val dueDateTextView: TextView = view.findViewById(R.id.dueDateTextView)

        // Set data to views
        titleTextView.text = task.title
        descriptionTextView.text = task.description
        dueDateTextView.text = "Due by: ${task.dueDate}"

        // Set the content view of the bottom sheet
        bottomSheetDialog.setContentView(view)

        // Show the bottom sheet
        bottomSheetDialog.show()
    }
}
