package com.example.products.model.network


import com.example.products.model.ProductsEntityItem
import retrofit2.Call

import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    fun fecthAllProducts(): Call<List<ProductsEntityItem>>

    @GET("products")
    suspend fun fetchAllCoroutines(): Response<List<ProductsEntityItem>>
}