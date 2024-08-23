package com.lgaieta.classmanager.subjects.services
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lgaieta.classmanager.subjects.models.Subject

@Entity(tableName = "subject")
data class SubjectRoomEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val name: String
) : Subject