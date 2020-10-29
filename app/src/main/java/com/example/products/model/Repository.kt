package com.example.products.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.products.model.retrofit.Products
import com.example.products.model.retrofit.RetrofitClient
import com.example.products.model.room.ProductsDao
import com.example.products.model.room.ProductsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val mProductsDao: ProductsDao) {

    private val retroServices = RetrofitClient.getRetrofitClient()

    val mProductos = mProductsDao.getAllProductsFromDB()
    val mDataProductsDBList = mutableListOf<Products>()

    fun getProductsFromServer() {

        val mCall = retroServices.fecthAllProducts()
        mCall.enqueue(object : Callback<List<ProductsItem>> {
            override fun onResponse(
                    call: Call<List<ProductsItem>>,
                    response: Response<List<ProductsItem>>
            ) {
                Log.d("Prueba", response.body().toString())
                when (response.code()) {

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
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

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })

    }

    fun getDataFromServerWithCoroutines() = CoroutineScope(Dispatchers.IO).launch {

        val service = kotlin.runCatching { retroServices.fetchAllCoroutines() }
        service.onSuccess { it ->
            when(it.code()){

                in 200..299 -> it.body()?.let {
                  mProductsDao.insertAllProducts(it)
                }

                in 300..599 -> Log.d("Response 300", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
        }
    }
            service.onFailure {
                Log.e("ERROR", it.message.toString())
            }
        
    }

   /* fun convert(mList: List<ProductsItem>): List<ProductsItem>{

        val listProducts = mutableListOf<ProductsItem>()

        mList.map {

            listProducts.add(ProductsItem(it.id,
            it.name,
            it.image,
            it.price))
        }

        return listProducts*/
    //}

    fun getOneById(id: String): LiveData<ProductsItem> {
        return mProductsDao.getCodigoByID(id)
    }

    fun getOneByImage(image: String): LiveData<ProductsItem> {
        return mProductsDao.getImageByID(image)
    }

    fun getOneByName(name: String): LiveData<ProductsItem> {
        return mProductsDao.getNameByID(name)
    }

    fun getOneByPrice(price: String): LiveData<ProductsItem>{
        return mProductsDao.getPriceByID(price)
    }


}
