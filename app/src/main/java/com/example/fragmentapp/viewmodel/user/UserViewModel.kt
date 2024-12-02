package com.example.fragmentapp.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentapp.model.UserModel

class UserViewModel : ViewModel() {

    private val usersLiveData: MutableLiveData<List<UserModel>> = MutableLiveData()

    var userList = mutableListOf(
        UserModel("Trần Hoàn", 23),
        UserModel("Trần Hoàn", 22),
        UserModel("Trần Hoàn", 23),
        UserModel("Trần Hoàn", 21)
    )

    fun getUsers(): LiveData<List<UserModel>> {
        usersLiveData.postValue(userList)
        return usersLiveData
    }

    fun setUser(name: String, age: Int) {
        var user = UserModel(name, age)
        userList.add(user)

        usersLiveData.postValue(userList)
    }
}