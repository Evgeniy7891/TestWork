package com.example.testwork.ui.fragments.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwork.data.repository.Repository
import com.example.testwork.model.store.Store
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    var repository = Repository()
    val phonesList : MutableLiveData<Response<Store>> = MutableLiveData()

    fun getPhones() {
        viewModelScope.launch {
            phonesList.value = repository.getPhones()
        }
    }

}