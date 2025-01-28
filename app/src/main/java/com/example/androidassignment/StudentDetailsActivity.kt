package com.example.androidassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidassignment.base.Constants.RESULT_DELETE
import com.example.androidassignment.base.Constants.RESULT_UPDATE
import com.google.android.material.appbar.MaterialToolbar

class StudentDetailsActivity : AppCompatActivity() {

    private val editStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_DELETE || result.resultCode == RESULT_UPDATE) {
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameTextView: TextView = findViewById(R.id.student_details_name_text_view)
        val idTextView: TextView = findViewById(R.id.student_details_id_text_view)
        val phoneTextView: TextView = findViewById(R.id.student_details_phone_text_view)
        val addressTextView: TextView = findViewById(R.id.student_details_address_text_view)
        val studentCheckBox: CheckBox = findViewById(R.id.student_details_check_box)
        val editButton: Button = findViewById(R.id.student_details_edit_button)
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        nameTextView.text = intent.getStringExtra("studentName")
        idTextView.text = intent.getStringExtra("studentId")
        phoneTextView.text = intent.getStringExtra("studentPhone")
        addressTextView.text = intent.getStringExtra("studentAddress")
        studentCheckBox.isChecked = intent.getBooleanExtra("isChecked", false)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("studentName", nameTextView.text)
                putExtra("studentId", idTextView.text)
                putExtra("studentPhone", phoneTextView.text)
                putExtra("studentAddress", addressTextView.text)
                putExtra("isChecked", studentCheckBox.isChecked)
            }
            editStudentLauncher.launch(intent)
        }

    }
}