package com.lgaieta.classmanager.subjects.services

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lgaieta.classmanager.subjects.models.Subject

@Entity(tableName = "subject")
data class SubjectRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val info: String?,
) {
    fun toSubject() = Subject(id, name, info)

    companion object {
        fun fromSubject(subject: Subject) = SubjectRoomEntity(subject.id, subject.name, subject.info)
    }
}