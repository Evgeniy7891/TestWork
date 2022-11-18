package com.example.testwork.data.api

import com.example.testwork.model.homestore.Store
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET ("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getPhones() : Response<Store>
}