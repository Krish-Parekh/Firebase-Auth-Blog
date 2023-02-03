package com.example.firebase_blog.ui.email_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_blog.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}