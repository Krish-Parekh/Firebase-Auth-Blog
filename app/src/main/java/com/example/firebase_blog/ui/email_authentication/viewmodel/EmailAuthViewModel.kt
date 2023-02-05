package com.example.firebase_blog.ui.email_authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_blog.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class LoginUiState(
    val userId: String,
    val username: String,
    val email: String,
)

class EmailAuthViewModel : ViewModel() {

    private val mAuth: FirebaseAuth by lazy { Firebase.auth }

    private val _loginStatus = MutableLiveData<Resource<LoginUiState>>()
    val loginStatus: LiveData<Resource<LoginUiState>> = _loginStatus

    private val _signupStatus = MutableLiveData<Resource<String>>()
    val signupStatus: LiveData<Resource<String>> = _signupStatus

    private val _resetPasswordStatus = MutableLiveData<Resource<String>>()
    val resetPasswordStatus: LiveData<Resource<String>> = _resetPasswordStatus

    private val _deleteAccountStatus = MutableLiveData<Resource<String>>()
    val deleteAccountStatus: LiveData<Resource<String>> = _deleteAccountStatus

    fun login(email: String, password: String) {
        viewModelScope.launch(IO) {
            try {
                _loginStatus.postValue(Resource.loading())
                mAuth.signInWithEmailAndPassword(email, password).await()
                val user = mAuth.currentUser!!
                val loginUiState = LoginUiState(
                    userId = user.uid,
                    username = user.displayName!!,
                    email = user.email!!
                )
                _loginStatus.postValue(Resource.success(loginUiState))

            } catch (exception: Exception) {
                _loginStatus.postValue(Resource.error("Login failed : ${exception.message}"))
            }
        }
    }

    fun signup(
        username: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch(IO) {
            try {
                _signupStatus.postValue(Resource.loading())
                mAuth.createUserWithEmailAndPassword(email, password)
                val user = mAuth.currentUser!!
                val profileBuilder = UserProfileChangeRequest.Builder()
                val profile = profileBuilder.setDisplayName(username).build()
                user.updateProfile(profile).await()
                _signupStatus.postValue(Resource.success("Signup success."))
            } catch (exception: Exception) {
                _signupStatus.postValue(Resource.error("Signup failed : ${exception.message}"))
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch(IO) {
            try {
                _resetPasswordStatus.postValue(Resource.loading())
                mAuth.sendPasswordResetEmail(email).await()
                _resetPasswordStatus.postValue(Resource.success("Resent email sent."))
            } catch (exception: Exception) {
                _resetPasswordStatus.postValue(Resource.error("Resent email failed : ${exception.message}"))
            }
        }
    }

    fun signOut() = mAuth.signOut()

    fun deleteAccount() {
        viewModelScope.launch(IO) {
            try {
                _deleteAccountStatus.postValue(Resource.loading())
                val user = mAuth.currentUser!!
                user.delete().await()
                _deleteAccountStatus.postValue(Resource.success("Delete account success."))
            } catch (exception: Exception) {
                _deleteAccountStatus.postValue(Resource.error("Delete account failed : ${exception.message}"))
            }
        }
    }
}