package com.example.fragmentapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fragmentapp.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao


}