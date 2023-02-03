package com.example.firebase_blog.ui.email_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_blog.databinding.ActivityEmailForgotPasswordBinding

class EmailForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}