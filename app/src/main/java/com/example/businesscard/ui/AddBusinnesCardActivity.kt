package com.example.businesscard.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ActivityAddBusinnesCardBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch

class AddBusinnesCardActivity : AppCompatActivity() {


    private var colorSelected: String = "#90a4ae"
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
        colorListener()
    }

    private fun closeListener() {
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun colorListener() {
        binding.imgColor.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(this)
                .setTitle(getString(R.string.label_select_color))
                .setColorShape(ColorShape.SQAURE)
                .setColorSwatch(ColorSwatch._300)
                .setDefaultColor(R.color.gray)
                .setColorListener { color, colorHex ->
                    binding.imgColor.setBackgroundColor(color)
                    colorSelected = colorHex
                }
                .show()
        }
    }
    
    private fun insertListener() {
        binding.btnConfirm.setOnClickListener {
            if (validarCampos()) {
                val businessCard = BusinessCard(
                    name = binding.tlName.editText?.text.toString(),
                    business = binding.tlNameBusiness.editText?.text.toString(),
                    phone = binding.tlPhone.editText?.text.toString(),
                    email = binding.tlEmailAddress.editText?.text.toString(),
                    backgroundColor = colorSelected
                )
                mainViewModel.insert(businessCard)
                Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun validarCampos(): Boolean {

        limparErros()

        var retorno = true

        if (TextUtils.isEmpty(binding.teName.text) || binding.teName.text == null) {
            binding.tlName.error = getString(R.string.error_input_name)
            binding.teName.requestFocus()
            retorno = false
        }

        if (TextUtils.isEmpty(binding.tePhone.text) || binding.tePhone.text == null) {
            binding.tlPhone.error = getString(R.string.error_input_phone)
            binding.tePhone.requestFocus()
            retorno = false
        }

        if (TextUtils.isEmpty(binding.teEmailAddress.text) || binding.teEmailAddress.text == null) {
            binding.tlEmailAddress.error = getString(R.string.error_input_email)
            binding.teEmailAddress.requestFocus()
            retorno = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.teEmailAddress.text.toString())
                .matches()
        ) {
            binding.tlEmailAddress.error = getString(R.string.error_input_email_invalid)
            binding.teEmailAddress.requestFocus()
            retorno = false
        }

        if (TextUtils.isEmpty(binding.teNameBusiness.text) || binding.teNameBusiness.text == null) {
            binding.tlNameBusiness.error = getString(R.string.error_input_name_business)
            binding.teNameBusiness.requestFocus()
            retorno = false
        }


        return retorno
    }

    private fun limparErros() {
        binding.tlName.error = null
        binding.tlPhone.error = null
        binding.tlEmailAddress.error = null
        binding.tlNameBusiness.error = null
    }
}