package com.example.testwork.data.api

import com.example.testwork.model.cart.Cart
import com.example.testwork.model.details.Details
import com.example.testwork.model.store.Store
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET ("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getPhones() : Response<Store>

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails(): Response<Details>

    @GET("/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): Response<Cart>
}