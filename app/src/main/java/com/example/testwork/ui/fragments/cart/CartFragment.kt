package com.example.testwork.ui.fragments.cart

import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testwork.R
import com.example.testwork.databinding.FragmentCartBinding
import com.example.testwork.databinding.FragmentDetailsBinding
import com.example.testwork.databinding.FragmentThirdBinding
import com.example.testwork.model.cart.Basket
import com.example.testwork.model.cart.Cart
import com.example.testwork.model.store.BestSeller
import com.example.testwork.model.store.Store
import com.example.testwork.ui.fragments.details.DetailsViewModel
import com.example.testwork.ui.fragments.main.MainAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()

 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     Log.d("TAG", "MAIN on create")
     _binding = FragmentCartBinding.inflate(inflater, container, false)
     viewModel.getCart()
     viewModel.cartList.observe(viewLifecycleOwner, {
         CoroutineScope(Dispatchers.Main).launch {
             binding.tvPriceTotal.setText("$"+it.body()?.total.toString())
             binding.tvDeliveryFree.setText(it.body()?.delivery)
             delay(500)
             Log.d("TAG", "MAIN")
             initialCart(it.body()?.basket!!)
         }
     })
        return binding.root
    }
    private fun initialCart(item: List<Basket>) {
        Log.d("TAG", "INIT")
        val cart: List<Basket> = item
        binding.recyclerviewCart.layoutManager = LinearLayoutManager(context)
        val data = cart
        val adapter = CartAdapter(data)
        binding.recyclerviewCart.adapter = adapter
    }
}