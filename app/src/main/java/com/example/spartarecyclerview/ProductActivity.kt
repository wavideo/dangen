package com.example.spartarecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spartarecyclerview.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        UserProductManager.defaultAdd()
        val products = UserProductManager.products

        val position = intent.getIntExtra("position",-1)
        binding.ivImg.setImageResource(products[position].img)
        binding.tvUserName.text = products[position].userName
        binding.tvRegion.text = products[position].region
        binding.tvTitle.text = products[position].title
        binding.tvBody.text = products[position].body
        binding.tvPrice.text = UserProductManager.convertWon(products[position].price)
    }
}