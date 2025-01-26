package com.example.androidassignment.adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.OnItemClickListener
import com.example.androidassignment.R
import com.example.androidassignment.model.Student

class StudentViewHolder(
    itemView: View,
    listener: OnItemClickListener?
) : RecyclerView.ViewHolder(itemView) {
    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var studentCheckBox: CheckBox? = null
    private var student: Student? = null

    val itemLayout: View = itemView.findViewById(R.id.itemLayout)

    init {
        nameTextView = itemView.findViewById(R.id.student_item_name_text_view)
        idTextView = itemView.findViewById(R.id.student_item_id_text_view)
        studentCheckBox = itemView.findViewById(R.id.student_item_check_box)

        studentCheckBox?.apply {
            setOnClickListener {
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                }
            }
        }

        itemView.setOnClickListener {
            Log.d("OnClick event", "On click listener $adapterPosition")
            listener?.onItemClick(student)
        }
    }

    fun bind(student: Student?, position: Int) {
        this.student = student
        nameTextView?.text = student?.name
        idTextView?.text = student?.id
        studentCheckBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position
        }
    }
}