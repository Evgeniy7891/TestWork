package com.example.testwork.ui.fragments.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwork.data.repository.Repository
import com.example.testwork.model.cart.Cart
import kotlinx.coroutines.launch
import retrofit2.Response

class CartViewModel : ViewModel() {
    var repository = Repository()
    val cartList: MutableLiveData<Response<Cart>> = MutableLiveData()

    fun getCart() {
        viewModelScope.launch {
            cartList.value = repository.getCart()
        }
    }
}