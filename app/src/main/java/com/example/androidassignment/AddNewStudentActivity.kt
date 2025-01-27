package com.example.androidassignment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidassignment.model.Model
import com.example.androidassignment.model.Student
import com.google.android.material.appbar.MaterialToolbar

class AddNewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_new_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameTextView: TextView = findViewById(R.id.add_new_student_name_text_view)
        val idTextView: TextView = findViewById(R.id.add_new_student_id_text_view)
        val phoneTextView: TextView = findViewById(R.id.add_new_student_phone_text_view)
        val addressTextView: TextView = findViewById(R.id.add_new_student_address_text_view)
        val saveButton: Button = findViewById(R.id.add_new_student_save_button)
        val cancelButton: Button = findViewById(R.id.add_new_student_cancel_button)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            val newStudent = Student(
                nameTextView.text.toString(),
                idTextView.text.toString(),
                phoneTextView.text.toString(),
                addressTextView.text.toString(),
                false
            )
            progressBar?.visibility = View.VISIBLE
            Model.shared.students.add(newStudent)
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}