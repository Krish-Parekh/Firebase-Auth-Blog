package com.example.firebase_blog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_blog.databinding.ActivityMainBinding
import com.example.firebase_blog.ui.email_authentication.EmailLoginActivity
import com.example.firebase_blog.ui.phone_authentication.PhoneActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.btnEmailAuth.setOnClickListener {
            val emailIntent = Intent(this, EmailLoginActivity::class.java)
            startActivity(emailIntent)
        }
        binding.btnPhoneAuth.setOnClickListener {
            val phoneIntent = Intent(this, PhoneActivity::class.java)
            startActivity(phoneIntent)
        }
    }
}