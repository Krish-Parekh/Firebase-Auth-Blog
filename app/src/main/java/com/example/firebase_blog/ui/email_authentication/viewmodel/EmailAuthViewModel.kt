package com.example.firebase_blog.ui.email_authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase_blog.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailAuthViewModel : ViewModel() {

    private val mAuth: FirebaseAuth by lazy { Firebase.auth }

    private val _loginState = MutableLiveData<Resource<String>>()
    val loginState: LiveData<Resource<String>> = _loginState

    private val _signupState = MutableLiveData<Resource<String>>()
    val signupState: LiveData<Resource<String>> = _signupState

    private val _resetPasswordState = MutableLiveData<Resource<String>>()
    val resetPasswordState: LiveData<Resource<String>> = _resetPasswordState

    fun login(email: String, password: String) {
        _loginState.postValue(Resource.loading())
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _loginState.postValue(Resource.success("Login success."))
            }
            .addOnFailureListener { exception ->
                _loginState.postValue(Resource.error("Login failed : ${exception.message}"))
            }
    }

    fun signup(
        username: String,
        email: String,
        password: String
    ) {
        _loginState.postValue(Resource.loading())
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = mAuth.currentUser!!
                val profileBuilder = UserProfileChangeRequest.Builder()
                val profile = profileBuilder.setDisplayName(username).build()
                user.updateProfile(profile)
                    .addOnSuccessListener {
                        _loginState.postValue(Resource.success("Signup success."))
                    }
                    .addOnFailureListener { exception ->
                        _loginState.postValue(Resource.error("Signup failed : ${exception.message}"))
                    }
            }
            .addOnFailureListener { exception ->
                _loginState.postValue(Resource.error("Signup failed : ${exception.message}"))
            }
    }

    fun resetPassword(email: String) {
        _resetPasswordState.postValue(Resource.loading())
        mAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                _resetPasswordState.postValue(Resource.success("Resent email sent."))
            }
            .addOnFailureListener { exception ->
                _resetPasswordState.postValue(Resource.error("Resent email failed : ${exception.message}"))
            }
    }
}