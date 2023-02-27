package com.example.firebase_blog.ui.phone_authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_blog.databinding.ActivityPhoneBinding
import com.example.firebase_blog.util.InputValidation
import com.example.firebase_blog.util.addTextWatcher
import com.example.firebase_blog.util.getValue

class PhoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBinding

    companion object {
        const val countryCode = "+91"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {

        binding.etPhoneContainer.addTextWatcher()

        binding.btnGetOtp.setOnClickListener {
            var phoneNumber = binding.etPhone.getValue()
            val (isNumberValid, numberError) = InputValidation.isPhoneNumberValid(phoneNumber)
            if (isNumberValid.not()) {
                binding.etPhoneContainer.error = numberError
            } else {
                val otpIntent = Intent(this, OtpActivity::class.java)
                phoneNumber = "$countryCode$phoneNumber"
                otpIntent.putExtra("PHONE_NUMBER", phoneNumber)
                startActivity(otpIntent)
            }
        }
    }
}