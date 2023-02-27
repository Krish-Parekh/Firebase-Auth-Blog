package com.example.firebase_blog.ui.phone_authentication.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_blog.util.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class PhoneViewModel : ViewModel() {
    private val mAuth: FirebaseAuth by lazy { Firebase.auth }
    private var mVerificationId: String? = null
    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null

    private var _otpStatus: MutableLiveData<Resource<String>> = MutableLiveData()
    var otpStatus: LiveData<Resource<String>> = _otpStatus

    private var _authStatus: MutableLiveData<Resource<String>> = MutableLiveData()
    var authStatus: LiveData<Resource<String>> = _authStatus

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(error: FirebaseException) {
            error.message?.let { message ->
                _otpStatus.postValue(Resource.error(message))
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            mVerificationId = verificationId
            mResendToken = token
            _otpStatus.postValue(Resource.success("OTP sent."))
        }
    }

    fun startVerification(phoneNumber: String, activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(optCode: String) {
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, optCode)
        signInWithPhoneAuthCredential(credential)
    }

    internal fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        viewModelScope.launch(IO) {
            try {
                _authStatus.postValue(Resource.loading())
                mAuth.signInWithCredential(credential).await()
                _authStatus.postValue(Resource.success("Authentication Success."))
            } catch (exception: FirebaseAuthInvalidCredentialsException) {
                exception.message?.let { message ->
                    _authStatus.postValue(Resource.error(message))
                }
            }
        }
    }
}