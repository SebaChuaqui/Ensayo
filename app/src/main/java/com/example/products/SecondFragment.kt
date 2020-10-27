package com.example.products

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.products.model.MyViewModel.ProductsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mProductsViewModel: ProductsViewModel

    var productsId = 0
    var productsName = "a"
    var productsPrice = 0
    var productsImage = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProductsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        arguments?.let {
            productsId = it.getInt("id")
            productsName = it.getString("name").toString()
            productsPrice = it.getInt("price")
            productsImage = it.getString("image")!!

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        namecel.text = productsName
        pricecel.text = productsPrice.toString()
        Glide.with(this)
                .load(productsImage)
                .into(imagecel)

        // view.findViewById<Button>(R.id.button_second).setOnClickListener {
            // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        // }

        fab.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(){


                val intent = Intent(Intent.ACTION_SEND)
                val to = "info@plaplix.cl"
                val addressees = arrayOf(to)
                val subject = "Asunto "
                val message = "Hola \nVi este super Heroe llamado  y quiero comprarlo llámame al _________"
                intent.putExtra(Intent.EXTRA_EMAIL, addressees)
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Contactar Area de Ventas:"))
            }
        }
    }
