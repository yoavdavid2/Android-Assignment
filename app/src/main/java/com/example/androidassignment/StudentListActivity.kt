package com.example.androidassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.adapter.StudentsRecyclerAdapter
import com.example.androidassignment.model.Model
import com.example.androidassignment.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface OnItemClickListener {
    fun onItemClick(position: Int, student: Student?)
}

class StudentListActivity : AppCompatActivity() {

    var students: MutableList<Student>? = null
    private lateinit var adapter: StudentsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.shared.students

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentsRecyclerAdapter(this, students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int, student: Student?) {
                Log.d("OnClick event", "On student clicked, name: ${student?.name}, position $position")
            }
        }
        recyclerView.adapter = adapter

        val addStudentButton: FloatingActionButton = findViewById(R.id.students_fab)

    }
}