package com.example.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.App
import com.example.businesscard.databinding.ActivityMainBinding
import com.example.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels{
        MainVIewModelFactory((application as App).repository)
    }
    private val adapter by lazy {
        BusinessCardAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdpter()
        insertListenner()
        getAllBusinessCard()
    }

    private fun setAdpter() {
        binding.rvCards.adapter = adapter
    }

    private fun insertListenner() {
        binding.btnFloating.setOnClickListener {
            val intent = Intent(this, AddBusinnesCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card->
            Image.share(this@MainActivity, card)
        }
    }

    private  fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this){
                adapter.submitList(it)
        }
    }
}