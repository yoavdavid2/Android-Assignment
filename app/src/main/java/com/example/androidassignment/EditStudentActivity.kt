package com.example.androidassignment

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidassignment.base.Constants.RESULT_DELETE
import com.example.androidassignment.base.Constants.RESULT_UPDATE
import com.example.androidassignment.model.Model
import com.google.android.material.appbar.MaterialToolbar

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameTextView: TextView = findViewById(R.id.student_form_name_text_view)
        val idTextView: TextView = findViewById(R.id.student_form_id_text_view)
        val phoneTextView: TextView = findViewById(R.id.student_form_phone_text_view)
        val addressTextView: TextView = findViewById(R.id.student_form_address_text_view)
        val studentCheckBox: CheckBox = findViewById(R.id.student_form_check_box)
        val saveButton: Button = findViewById(R.id.student_form_save_button)
        val cancelButton: Button = findViewById(R.id.student_form_cancel_button)
        val deleteButton: Button = findViewById(R.id.student_form_delete_button)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)

        val studentId = intent.getStringExtra("studentId")

        nameTextView.text = intent.getStringExtra("studentName")
        idTextView.text = studentId
        phoneTextView.text = intent.getStringExtra("studentPhone")
        addressTextView.text = intent.getStringExtra("studentAddress")
        studentCheckBox.isChecked = intent.getBooleanExtra("isChecked", false)


        toolbar.setNavigationOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            val student = Model.shared.students.find { it.id == studentId }
            if (student != null) {
                Model.shared.students.remove(student)
            }
            setResult(RESULT_DELETE)
            finish()
        }

        saveButton.setOnClickListener {
            val newName = nameTextView.text.toString()
            val newPhone = phoneTextView.text.toString()
            val newAddress = addressTextView.text.toString()
            val isCheckedStatus = studentCheckBox.isChecked

            Model.shared.students.find { it.id == studentId }?.apply {
                name = newName
                phone = newPhone
                address = newAddress
                isChecked = isCheckedStatus
            }
            setResult(RESULT_UPDATE)
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}