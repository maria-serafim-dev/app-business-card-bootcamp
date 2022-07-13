package com.example.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.databinding.ActivityAddBusinnesCardBinding

class AddBusinnesCardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBusinnesCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBusinnesCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListenner()
    }

    private fun insertListenner() {
        binding.btnClose.setOnClickListener {
           finish()
        }
    }
}