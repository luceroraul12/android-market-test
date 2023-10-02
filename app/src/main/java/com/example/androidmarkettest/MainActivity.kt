package com.example.androidmarkettest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmarkettest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val products: List<Product> = listOf(
        Product("mix", "cereales", 2400, "x100g", 0, 0),
        Product("mix", "legumbres", 350, "x100g", 0, 0),
        Product("harina", "integral", 100, "x100g", 0, 0),
        Product("harina", "de maiz", 900, "x100g", 0, 0),
    )

    private lateinit var binding:  ActivityMainBinding

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListener()
    }

    private fun initUI() {
        productAdapter = ProductAdapter(products)
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = productAdapter
    }

    private fun initListener() {
        binding.bCart.setOnClickListener { goToCart() }
    }

    private fun goToCart(){
        val intent = Intent(this, ShoppingCartActivity::class.java)
        startActivity(intent)
    }
}