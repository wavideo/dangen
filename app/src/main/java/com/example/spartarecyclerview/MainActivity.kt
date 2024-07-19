package com.example.spartarecyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spartarecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 바인딩에 인플레이터 달고, root 세팅
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        UserProductManager.defaultAdd()
        var products = UserProductManager.products
        val adapter = ProductAdapter(products)
        binding.recyclerViewMain.adapter = adapter
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : ProductAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intentMainToProduct:Intent = Intent(this@MainActivity, ProductActivity::class.java)
                intentMainToProduct.putExtra("position", position)
                startActivity(intentMainToProduct)
            }
        }

    }
}