package com.example.firebase_blog.ui.email_authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firebase_blog.databinding.ActivityEmailHomeBinding
import com.example.firebase_blog.ui.email_authentication.viewmodel.EmailAuthViewModel

class EmailHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailHomeBinding
    private val emailAuthViewModel by viewModels<EmailAuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        val bundle = intent.extras!!
        val uid = bundle.getString(EmailLoginActivity.UID)
        val username = bundle.getString(EmailLoginActivity.USERNAME)
        val email = bundle.getString(EmailLoginActivity.EMAIL)

        binding.userId.text = uid
        binding.username.text = username
        binding.email.text = email

        binding.btnLogout.setOnClickListener {
            emailAuthViewModel.signOut()
            navigateToLogin()
        }

        binding.btnDeleteAccount.setOnClickListener {
            emailAuthViewModel.deleteAccount()
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        finish()
        val loginIntent = Intent(this, EmailLoginActivity::class.java)
        startActivity(loginIntent)
    }
}