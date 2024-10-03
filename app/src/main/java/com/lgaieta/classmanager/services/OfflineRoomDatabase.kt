package com.lgaieta.classmanager.services

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.lgaieta.classmanager.subjects.services.SubjectRoomDao
import com.lgaieta.classmanager.subjects.services.SubjectRoomEntity
import com.lgaieta.classmanager.tasks.services.TaskRoomDao
import com.lgaieta.classmanager.tasks.services.TaskRoomEntity

@Database(entities = [SubjectRoomEntity::class, TaskRoomEntity::class], version = 3, exportSchema = false)
abstract class OfflineRoomDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectRoomDao
    abstract fun taskDao(): TaskRoomDao


    companion object {
        @Volatile
        private var Instance: OfflineRoomDatabase? = null

        fun getDatabase(context: Context): OfflineRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OfflineRoomDatabase::class.java, "offline_classmanager")
                    .fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}