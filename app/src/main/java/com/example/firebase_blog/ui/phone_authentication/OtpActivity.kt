package com.example.firebase_blog.ui.phone_authentication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_blog.R
import com.example.firebase_blog.databinding.ActivityOtpBinding
import com.example.firebase_blog.ui.phone_authentication.viewmodel.PhoneViewModel
import com.example.firebase_blog.util.LoadingDialog
import com.example.firebase_blog.util.Status.*
import com.example.firebase_blog.util.getValue
import com.example.firebase_blog.util.handleKeyEvent
import com.example.firebase_blog.util.handleTextChange

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private val phoneViewModel by viewModels<PhoneViewModel>()
    private val loadingDialog by lazy { LoadingDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupEditTextListener()
        setupObserver()
    }

    private fun setupUI() {
        val bundle = intent.extras!!
        if (bundle.containsKey("PHONE_NUMBER")) {
            val phoneNumber = bundle.getString("PHONE_NUMBER")!!
            binding.otpSubHeader.text = getString(R.string.sub_header_verify_otp, phoneNumber)
            phoneViewModel.startVerification(phoneNumber, this)
        }
        with(binding) {
            btnOtpVerification.setOnClickListener {
                val code1 = etOtpCode1.getValue()
                val code2 = etOtpCode2.getValue()
                val code3 = etOtpCode3.getValue()
                val code4 = etOtpCode4.getValue()
                val code5 = etOtpCode5.getValue()
                val code6 = etOtpCode6.getValue()
                val otpCode = "$code1$code2$code3$code4$code5$code6"
                phoneViewModel.verifyCode(otpCode)
            }
        }
    }

    private fun setupObserver() {
        phoneViewModel.otpStatus.observe(this) { resource ->
            when (resource.status) {
                LOADING -> Unit
                SUCCESS -> {
                    val message = resource.data
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
                ERROR -> {
                    val error = resource.message
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        phoneViewModel.authStatus.observe(this) { resource ->
            when (resource.status) {
                LOADING -> {
                    loadingDialog.show()
                }
                SUCCESS -> {
                    val message = resource.data
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
                ERROR -> {
                    val error = resource.message
                    Log.d("OtpActivityTAG", "setupObserver: $error")
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        }
    }

    private fun setupEditTextListener() {
        with(binding) {
            etOtpCode1.handleTextChange(etOtpCode2)
            etOtpCode2.handleTextChange(etOtpCode3)
            etOtpCode3.handleTextChange(etOtpCode4)
            etOtpCode4.handleTextChange(etOtpCode5)
            etOtpCode5.handleTextChange(etOtpCode6)

            etOtpCode2.handleKeyEvent(etOtpCode1)
            etOtpCode3.handleKeyEvent(etOtpCode2)
            etOtpCode4.handleKeyEvent(etOtpCode3)
            etOtpCode5.handleKeyEvent(etOtpCode4)
            etOtpCode6.handleKeyEvent(etOtpCode5)
        }
    }
}