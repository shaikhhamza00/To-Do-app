package com.hamza_dev.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : BottomSheetDialogFragment() {

    interface OnTaskAddedListener {
        fun onTaskAdded(title: String, description: String, endDate: String)
    }

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var endDateEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private var onTaskAddedListener: OnTaskAddedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find views by ID
        titleEditText = view.findViewById(R.id.titleEditText)
        descriptionEditText = view.findViewById(R.id.descriptionEditText)
        endDateEditText = view.findViewById(R.id.endDateEditText)
        saveButton = view.findViewById(R.id.saveButton)
        cancelButton = view.findViewById(R.id.cancelButton)

        // Handle the date field click
        endDateEditText.setOnClickListener { showDatePicker() }

        // Handle the save button click
        saveButton.setOnClickListener {
            // Get the entered data
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val endDate = endDateEditText.text.toString()

            // Notify the listener (MainActivity) about the new task
            onTaskAddedListener?.onTaskAdded(title, description, endDate)

            // Dismiss the bottom sheet
            dismiss()
        }

        // Handle the cancel button click
        cancelButton.setOnClickListener {
            // Dismiss the bottom sheet
            dismiss()
        }
    }

    // Function to show the date picker
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                // Format the selected date and set it to the end date field
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                endDateEditText.setText(sdf.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    fun setOnTaskAddedListener(listener: OnTaskAddedListener) {
        onTaskAddedListener = listener
    }
}
