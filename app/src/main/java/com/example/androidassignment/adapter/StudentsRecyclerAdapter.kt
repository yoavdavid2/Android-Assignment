package com.example.androidassignment.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.OnItemClickListener
import com.example.androidassignment.R
import com.example.androidassignment.StudentDetailsActivity
import com.example.androidassignment.model.Student

class StudentsRecyclerAdapter(
    private val context: Context,
    private val students: MutableList<Student>?
) : RecyclerView.Adapter<StudentViewHolder>() {
    var listener: OnItemClickListener? = null

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.student_list_item,
            parent,
            false
        )

        return StudentViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(
            student = students?.get(position),
            position = position
        )

        holder.itemLayout.setOnClickListener {
            val intent = Intent(context, StudentDetailsActivity::class.java).apply {
                putExtra("studentName", students?.get(position)?.name)
                putExtra("studentId", students?.get(position)?.id)
                putExtra("studentPhone", students?.get(position)?.phone)
                putExtra("studentAddress", students?.get(position)?.address)
                putExtra("isChecked", students?.get(position)?.isChecked)
            }

            if (context is Activity) {
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                context.startActivity(intent, options.toBundle())
            }
        }
    }
}