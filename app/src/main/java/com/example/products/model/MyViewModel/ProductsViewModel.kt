package com.example.products.model.MyViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.products.model.ProductsDB
import com.example.products.model.ProductsEntityItem
import com.example.products.model.Repository

class ProductsViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository: Repository
    val mAllProducts: LiveData<List<ProductsEntityItem>>

    init {
        val mProductsDao = ProductsDB.getProductsDataBase(application).getProductsDao()
        mRepository = Repository(mProductsDao)
        mAllProducts = mRepository.mProductos
        mRepository.getProductsFromServer()

    }

    fun getOneID(id: String): LiveData<ProductsEntityItem> {
        return mRepository.getOneById(id)
    }

    fun getOneImage(image: String): LiveData<ProductsEntityItem> {
        return mRepository.getOneByImage(image)
    }

    fun getOneName(name: String): LiveData<ProductsEntityItem> {
        return mRepository.getOneByName(name)
    }

    fun getOnePrice(price: String): LiveData<ProductsEntityItem> {
        return mRepository.getOneByPrice(price)
    }
}