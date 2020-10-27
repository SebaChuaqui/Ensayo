package com.example.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.products.model.MyViewModel.Adapter
import com.example.products.model.MyViewModel.PassProducts
import com.example.products.model.MyViewModel.ProductsViewModel
import com.example.products.model.room.ProductsItem
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(), PassProducts {

    lateinit var mProductsViewModel: ProductsViewModel
    lateinit var mAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProductsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        mAdapter = Adapter(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mProductsRecyclerView = recyclerView

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        mProductsViewModel.mAllProducts.observe(viewLifecycleOwner, Observer {
            mAdapter.updateListProducts(it)
        })

        //view.findViewById<Button>(R.id.button_first).setOnClickListener {

        // }
    }

    override fun passProducts(mProductsItem: ProductsItem) {

        val mBundle = Bundle()
        mBundle.putInt("id", mProductsItem.id)
        mBundle.putString("name", mProductsItem.name)
        mBundle.putInt("price", mProductsItem.price)
        mBundle.putString("image", mProductsItem.image )
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }

}