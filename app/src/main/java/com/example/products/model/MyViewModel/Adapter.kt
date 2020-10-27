package com.example.products.model.MyViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.products.R
import com.example.products.model.room.ProductsItem
import kotlinx.android.synthetic.main.products.view.*

class Adapter(var mPassProducts: PassProducts ): RecyclerView.Adapter<Adapter.ViewHolderProducts>() {

    private var dataList = emptyList<ProductsItem>()

    fun updateListProducts(mDataList: List<ProductsItem>){

        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class ViewHolderProducts(itemView: View): RecyclerView.ViewHolder(itemView){

        val mimage = itemView.image
        val mmane = itemView.name
        val mprice = itemView.price
        val itemView = itemView.setOnClickListener{

            mPassProducts.passProducts(dataList[adapterPosition])
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProducts {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products, parent, false)
        return ViewHolderProducts(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderProducts, position: Int) {

        val products1: ProductsItem = dataList[position]
        holder.mmane.text = products1.name.capitalize()
        holder.mprice.text = products1.price.toString()
        Glide.with(holder.itemView.context)
                .load(products1.image)
                .into(holder.mimage)

    }

    override fun getItemCount() = dataList.size

    }


interface PassProducts{

    fun passProducts(mProductsItem: ProductsItem )
}