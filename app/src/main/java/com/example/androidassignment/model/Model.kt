package com.example.androidassignment.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..10) {
            val student = Student(
                name = "John Doe",
                id = i.toString(),
                phone = "050-4445556",
                address = "35 Fame avenue, Log valley",
                isChecked = false
            )
            students.add(student)
        }
    }
}