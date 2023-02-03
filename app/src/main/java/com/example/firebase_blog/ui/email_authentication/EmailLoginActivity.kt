package com.example.firebase_blog.ui.email_authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_blog.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.ctaSignup.setOnClickListener {
            val signupIntent = Intent(this, EmailSignupActivity::class.java)
            startActivity(signupIntent)
        }

        binding.ctaForgotPassword.setOnClickListener {
            val forgetPassIntent = Intent(this, EmailForgotPasswordActivity::class.java)
            startActivity(forgetPassIntent)
        }
    }
}