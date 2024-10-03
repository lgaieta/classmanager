package com.lgaieta.classmanager.students.services

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lgaieta.classmanager.students.models.Student


@Entity(tableName = "student")
data class StudentRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
) {
    fun toStudent() = Student(id, name)

    companion object {
        fun fromStudent(task: Student) = StudentRoomEntity(task.id, task.name)
    }
}

@Entity(tableName = "subject_student", primaryKeys = ["subjectId", "studentId"])
data class SubjectStudentCrossRef(
    val subjectId: Int,
    val studentId: Int
)