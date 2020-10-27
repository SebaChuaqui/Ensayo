package com.example.products.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(mProductsList: List<ProductsItem>)

    @Query("SELECT * FROM products_table")
    fun getAllProductsFromDB(): LiveData<List<ProductsItem>>

    @Query("SELECT * FROM products_table WHERE id=:id")
    fun getCodigoByID(id: String): LiveData<ProductsItem>

    @Query("SELECT * FROM products_table WHERE image=:image")
    fun getImageByID(image: String): LiveData<ProductsItem>

    @Query("SELECT * FROM products_table WHERE name=:name")
    fun getNameByID(name: String): LiveData<ProductsItem>

    @Query("SELECT * FROM products_table WHERE price=:price")
    fun getPriceByID(price: String): LiveData<ProductsItem>

}