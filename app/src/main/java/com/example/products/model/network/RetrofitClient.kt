package com.example.products.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object{

        private const val URL_BASE = "http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getRetrofitClient(): ProductsApi{

            val mRetorfit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetorfit.create(ProductsApi::class.java)
        }
    }
}