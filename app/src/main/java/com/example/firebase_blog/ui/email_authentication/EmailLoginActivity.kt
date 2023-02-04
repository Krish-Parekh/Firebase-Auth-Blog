package com.example.firebase_blog.ui.email_authentication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_blog.databinding.ActivityEmailLoginBinding
import com.example.firebase_blog.databinding.LoadingDialogBinding
import com.example.firebase_blog.ui.email_authentication.viewmodel.EmailAuthViewModel
import com.example.firebase_blog.ui.email_authentication.viewmodel.LoginUiState
import com.example.firebase_blog.util.*
import com.example.firebase_blog.util.Status.*

class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailLoginBinding
    private val emailAuthViewModel by viewModels<EmailAuthViewModel>()
    private val loadingDialog by lazy { LoadingDialog(this) }
    companion object {
        const val UID = "uid"
        const val USERNAME = "username"
        const val EMAIL = "email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
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

        binding.etEmailContainer.addTextWatcher()
        binding.etPasswordContainer.addTextWatcher()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.getValue()
            val password = binding.etPassword.getValue()
            if (verifyDetails(email, password)) {
                emailAuthViewModel.login(email, password)
            }
        }
    }

    private fun setupObserver() {
        emailAuthViewModel.loginStatus.observe(this) { login ->
            when (login.status) {
                LOADING -> {
                    loadingDialog.show()
                }
                SUCCESS -> {
                    loadingDialog.dismiss()
                    val state = login.data!!
                    navigateToHome(state)
                }
                ERROR -> {
                    loadingDialog.dismiss()
                    val error = login.message!!
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToHome(state: LoginUiState) {
        val homeIntent = Intent(this, EmailHomeActivity::class.java)
        homeIntent.putExtra(UID, state.userId)
        homeIntent.putExtra(USERNAME, state.username)
        homeIntent.putExtra(EMAIL, state.email)
        startActivity(homeIntent)
        finish()
    }

    private fun verifyDetails(email: String, password: String): Boolean {
        val (isEmailValid, emailError) = InputValidation.isEmailValid(email)
        if (isEmailValid.not()) {
            binding.etEmailContainer.error = emailError
            return isEmailValid
        }

        val (isPasswordValid, passwordError) = InputValidation.isPasswordValid(password)
        if (isPasswordValid.not()) {
            binding.etPasswordContainer.error = passwordError
            return isPasswordValid
        }
        return true
    }
}