package com.example.products.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.products.model.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val mProductsDao: ProductsDao) {

    private val service = RetrofitClient.getRetrofitClient()

    val mProductos = mProductsDao.getAllProductsFromDB()
    val mDataProductsDBList = mutableListOf<ProductsEntity>()

    fun getProductsFromServer(){

        val mCall = service.fecthAllProducts()
        mCall.enqueue(object : Callback<List<ProductsEntityItem>>{
            override fun onResponse(
                call: Call<List<ProductsEntityItem>>,
                response: Response<List<ProductsEntityItem>>
            ) {
                Log.d("Prueba", response.body().toString())
                when (response.code()){

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch{
                        response.body()?.let {
                            Log.d("productos", it.toString())
                            mProductsDao.insertAllProducts(it)
                        }
                    }

                    in 300..399 -> Log.d("Error 300", response.errorBody().toString())
                    in 400..499 -> Log.d("Error 400", response.errorBody().toString())
                    in 500..599 -> Log.d("Error 500", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<ProductsEntityItem>>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })

        fun getOneById(id :String): LiveData<ProductsEntityItem>{
            return mProductsDao.getCodigoByID()
        }

        fun getOneByImage(image: String): LiveData<ProductsEntityItem>{
            return mProductsDao.getImageByID()
        }

        fun getOneByName(name: String): LiveData<ProductsEntityItem>{
            return mProductsDao.getNameByID()
        }

        fun getOneByPrice(price: String): LiveData<ProductsEntityItem>{
            return mProductsDao.getPriceByID()
        }


    }
}