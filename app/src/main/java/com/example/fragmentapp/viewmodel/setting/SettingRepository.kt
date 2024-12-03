package com.example.fragmentapp.viewmodel.setting

import androidx.lifecycle.LiveData
import com.example.fragmentapp.data.local.AppDatabase
import com.example.fragmentapp.entity.User

class SettingRepository(appDatabase: AppDatabase) {
    private val userDao = appDatabase.userDao()
    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAll()
    }
    fun insertUser(user: User) {
        userDao.insert(user)
    }
}