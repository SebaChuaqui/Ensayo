package com.example.products.model.retrofit


import com.example.products.model.room.ProductsItem
import retrofit2.Call

import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    fun fecthAllProducts(): Call<List<ProductsItem>>

    @GET("products")
    suspend fun fetchAllCoroutines(): Response<List<ProductsItem>>
}