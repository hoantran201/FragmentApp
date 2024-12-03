package com.example.fragmentapp.viewmodel.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.fragmentapp.data.local.AppDatabase
import com.example.fragmentapp.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel(application: Application) : AndroidViewModel(application) {

    private val settingRepository: SettingRepository
    val allUsers: LiveData<List<User>>

    init {
        val appDatabase = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "database-local"
        ).build()

        settingRepository = SettingRepository(appDatabase)
        allUsers = settingRepository.getAllUsers()
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                settingRepository.insertUser(user)
            }
        }
    }
}