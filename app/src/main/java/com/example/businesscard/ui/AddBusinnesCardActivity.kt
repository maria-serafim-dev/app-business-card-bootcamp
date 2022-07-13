package com.example.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ActivityAddBusinnesCardBinding

class AddBusinnesCardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBusinnesCardBinding
    private val mainViewModel : MainViewModel by viewModels{
        MainVIewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBusinnesCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        closeListener()
        insertListener()
    }

    private fun closeListener() {
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun insertListener() {
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tlName.editText?.text.toString(),
                business = binding.tlNameBusiness.editText?.text.toString(),
                phone = binding.tlPhone.editText?.text.toString(),
                email = binding.tlEmailAddress.editText?.text.toString(),
                backgroundColor = binding.tlColor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_LONG).show()
            finish()
        }
    }
}