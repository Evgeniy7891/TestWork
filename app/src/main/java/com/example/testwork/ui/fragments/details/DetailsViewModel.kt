package com.example.testwork.ui.fragments.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwork.data.repository.Repository
import com.example.testwork.model.details.Details
import com.example.testwork.model.store.Store
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    var repository = Repository()
    val detailList : MutableLiveData<Response<Details>> = MutableLiveData()

    fun getDetails() {
        viewModelScope.launch {
            detailList.value = repository.getDetails()
        }
    }
}