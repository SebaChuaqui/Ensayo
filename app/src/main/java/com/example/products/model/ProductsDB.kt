package com.example.products.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



private const val DATA_BASE_NAME="products_db"

@Database(entities = [ProductsEntityItem::class], version = 1)


abstract class ProductsDB: RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    companion object{

        @Volatile

        private var INSTANCE: ProductsDB? = null

        fun getProductsDataBase(context: Context): ProductsDB{

            val tempInterface = INSTANCE
            if(tempInterface != null){
                return tempInterface
            }

          synchronized(this){
              val instance = Room.databaseBuilder(
                  context,
                  ProductsDB::class.java,
                  DATA_BASE_NAME
              )
                  .build()
              INSTANCE = instance
              return instance
          }

        }
    }

}