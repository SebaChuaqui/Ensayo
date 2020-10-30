package com.example.products

import com.example.products.model.retrofit.ProductsApi
import com.example.products.model.room.ProductsItem
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mProductsApi : ProductsApi

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder()
                .baseUrl(mMockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mProductsApi = mRetrofit.create(ProductsApi::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test

    fun getAllProducts_case() = runBlocking {
        //given
        val mResultList = listOf<ProductsItem>(ProductsItem(2,
                "https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/nova7-se/img/mob/huawei-nova7-se-mob.png",
                "Huawei Nova 7 SE 128GB",
                347760))

        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mResultList)))

        //when

        val result = mProductsApi.fetchAllCoroutines()

        //then

        assertThat(result).isNotNull()
        val body = result.body()
        assertThat(body).hasSize(2)
        assertThat(body?.get(0)?.id).isEqualTo(2)
        val request = mMockWebServer.takeRequest()
        println(request.path)
        Truth.assertThat(request.path).isEqualTo("/products")
    }
    }






