package com.example.testwork.data.repository

import com.example.testwork.data.api.RetrofitInstance
import com.example.testwork.model.homestore.Store
import retrofit2.Response

class Repository {
    suspend fun getPhones(): Response<Store> {
        return RetrofitInstance.api.getPhones()
    }
}