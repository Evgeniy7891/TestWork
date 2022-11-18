package com.example.testwork.ui.fragments.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwork.data.repository.Repository
import com.example.testwork.model.homestore.Store
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    var repository = Repository()
    val phonesList : MutableLiveData<Response<Store>> = MutableLiveData()

    fun getNalMoney() {
        viewModelScope.launch {
            phonesList.value = repository.getPhones()
        }
    }
}