package com.example.firebase_blog.ui.email_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_blog.databinding.ActivityEmailHomeBinding

class EmailHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}