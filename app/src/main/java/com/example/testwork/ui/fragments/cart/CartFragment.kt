package com.example.testwork.ui.fragments.cart


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testwork.R
import com.example.testwork.databinding.FragmentCartBinding
import com.example.testwork.model.cart.Basket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel.getCart()
        viewModel.cartList.observe(viewLifecycleOwner, {
            CoroutineScope(Dispatchers.Main).launch {
                binding.tvPriceTotal.setText("$" + it.body()?.total.toString())
                binding.tvDeliveryFree.setText(it.body()?.delivery)
                delay(500)
                initialCart(it.body()?.basket!!)
            }
            binding.ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        })
        return binding.root
    }

    private fun initialCart(item: List<Basket>) {
        val cart: List<Basket> = item
        binding.recyclerviewCart.layoutManager = LinearLayoutManager(context)
        val data = cart
        val adapter = CartAdapter(data)
        binding.recyclerviewCart.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}