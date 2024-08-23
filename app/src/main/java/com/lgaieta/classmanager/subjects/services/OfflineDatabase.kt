package com.lgaieta.classmanager.subjects.services

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database

@Database(entities = [SubjectRoomEntity::class], version = 1, exportSchema = false)
abstract class OfflineDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectRoomDao

    companion object {
        @Volatile
        private var Instance: OfflineDatabase? = null

        fun getDatabase(context: Context): OfflineDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OfflineDatabase::class.java, "offline_classmanager")
                    .build().also { Instance = it }
            }
        }
    }
}