package com.example.firebase_blog.ui.email_authentication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_blog.databinding.ActivityEmailForgotPasswordBinding
import com.example.firebase_blog.ui.email_authentication.viewmodel.EmailAuthViewModel
import com.example.firebase_blog.util.*
import com.example.firebase_blog.util.Status.*

class EmailForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailForgotPasswordBinding
    private val emailAuthViewModel by viewModels<EmailAuthViewModel>()
    private val loadingDialog by lazy { LoadingDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.etEmailContainer.addTextWatcher()

        binding.btnSendEmail.setOnClickListener {
            val email = binding.etEmail.getValue()
            if (verifyDetail(email)) {
                emailAuthViewModel.resetPassword(email)
            }
        }
    }

    private fun setupObserver() {
        emailAuthViewModel.resetPasswordStatus.observe(this) { resetPassword ->
            when (resetPassword.status) {
                LOADING -> {
                    loadingDialog.show()
                }
                SUCCESS -> {
                    loadingDialog.dismiss()
                    val state = resetPassword.data!!
                    Toast.makeText(this, state, Toast.LENGTH_SHORT).show()
                    finish()
                }
                ERROR -> {
                    loadingDialog.dismiss()
                    val error = resetPassword.message!!
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verifyDetail(email: String): Boolean {
        val (isEmailValid, emailError) = InputValidation.isEmailValid(email)
        if (isEmailValid.not()) {
            binding.etEmailContainer.error = emailError
            return isEmailValid
        }
        return true
    }
}