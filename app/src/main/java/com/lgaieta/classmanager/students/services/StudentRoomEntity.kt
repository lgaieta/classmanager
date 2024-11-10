package com.lgaieta.classmanager.students.services

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentWithNote


@Entity(tableName = "student")
data class StudentRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
) {
    fun toStudent() = Student(id, name)

    companion object {
        fun fromStudent(task: Student) = StudentRoomEntity(task.id, task.name)
    }
}

@Entity(tableName = "subject_student", primaryKeys = ["subjectId", "studentId"])
data class SubjectStudentCrossRef(
    val subjectId: Int,
    val studentId: Long
)

@Entity(tableName = "task_student", primaryKeys = ["taskId", "studentId"])
data class TaskStudentCrossRef(
    val taskId: Int,
    val studentId: Long,
    val note: Float
)

data class StudentRoomEntityWithNote(
    @Embedded val student: StudentRoomEntity,
    val note: Float
) {
    fun toStudentWithNote() = StudentWithNote(student = student.toStudent(), note = note)
}