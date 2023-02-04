package com.example.firebase_blog.ui.email_authentication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_blog.databinding.ActivityEmailSignupBinding
import com.example.firebase_blog.ui.email_authentication.viewmodel.EmailAuthViewModel
import com.example.firebase_blog.util.InputValidation
import com.example.firebase_blog.util.LoadingDialog
import com.example.firebase_blog.util.Status.*
import com.example.firebase_blog.util.addTextWatcher
import com.example.firebase_blog.util.getValue

class EmailSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailSignupBinding
    private val emailAuthViewModel by viewModels<EmailAuthViewModel>()
    private val loadingDialog by lazy { LoadingDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.ctaLogin.setOnClickListener {
            finish()
        }

        binding.etUsernameContainer.addTextWatcher()
        binding.etEmailContainer.addTextWatcher()
        binding.etPasswordContainer.addTextWatcher()

        binding.btnSignup.setOnClickListener {
            val username = binding.etUsername.getValue()
            val email = binding.etEmail.getValue()
            val password = binding.etPassword.getValue()
            if (verifyDetails(username, email, password)) {
                emailAuthViewModel.signup(username, email, password)
            }
        }
    }

    private fun setupObserver() {
        emailAuthViewModel.signupStatus.observe(this) { signup ->
            when (signup.status) {
                LOADING -> {
                    loadingDialog.show()
                }
                SUCCESS -> {
                    loadingDialog.dismiss()
                    val state = signup.data!!
                    Toast.makeText(this, state, Toast.LENGTH_SHORT).show()
                    finish()
                }
                ERROR -> {
                    loadingDialog.dismiss()
                    val error = signup.message!!
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verifyDetails(
        username: String,
        email: String,
        password: String
    ): Boolean {
        val (isUsernameValid, usernameError) = InputValidation.isUsernameValid(username)
        if (isUsernameValid.not()) {
            binding.etUsernameContainer.error = usernameError
            return isUsernameValid
        }

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