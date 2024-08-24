package com.lgaieta.classmanager.subjects.services

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database

@Database(entities = [SubjectRoomEntity::class], version = 1, exportSchema = false)
abstract class OfflineRoomDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectRoomDao

    companion object {
        @Volatile
        private var Instance: OfflineRoomDatabase? = null

        fun getDatabase(context: Context): OfflineRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OfflineRoomDatabase::class.java, "offline_classmanager")
                    .build().also { Instance = it }
            }
        }
    }
}