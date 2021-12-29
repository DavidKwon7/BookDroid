package com.example.bookdroid

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookdroid.DAO.HistoryDao
import com.example.bookdroid.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
}