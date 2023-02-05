package com.example.firebase_blog.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class InputValidationTest {
    // Username Validation
    @Test
    fun `isUsernameValid should return false when username is empty`() {
        val (valid, message) = InputValidation.isUsernameValid("")
        assertThat(valid).isFalse()
        assertThat(message).isEqualTo("Username cannot be empty.")
    }

    @Test
    fun `isUsernameValid should return false when username is more than 100 characters`() {
        val (valid, message) = InputValidation.isUsernameValid("a".repeat(101))
        assertThat(valid).isFalse()
        assertThat(message).isEqualTo("Username cannot be more than 100 characters.")
    }

    @Test
    fun `isUsernameValid should return false when username contains non-alphanumeric characters`() {
        val (valid, message) = InputValidation.isUsernameValid("user@name")
        assertThat(valid).isFalse()
        assertThat(message).isEqualTo("Username can only contain alphabets and numbers.")
    }

    @Test
    fun `isUsernameValid should return true when username is valid`() {
        val (valid, message) = InputValidation.isUsernameValid("username")
        assertThat(valid).isTrue()
        assertThat(message).isEqualTo("")
    }

    // Email Validation
    @Test
    fun `isEmailValid should return false when email is empty`() {
        val (valid, message) = InputValidation.isEmailValid("")
        assertThat(valid).isFalse()
        assertThat(message).isEqualTo("Email cannot be empty.")
    }

    @Test
    fun `isEmailValid should return false when email is not valid`() {
        val (valid, message) = InputValidation.isEmailValid("notvalidemail")
        assertThat(valid).isFalse()
        assertThat(message).isEqualTo("Email is not valid.")
    }

    @Test
    fun `isEmailValid should return true when email is valid`() {
        val (valid, message) = InputValidation.isEmailValid("valid@email.com")
        assertThat(valid).isTrue()
        assertThat(message).isEqualTo("")
    }

    // Password Validation
    @Test
    fun `isPasswordValid should return false when password is not valid`() {
        val (valid, message) = InputValidation.isPasswordValid("password")
        assertThat(valid).isFalse()
        assertThat(message).isEqualTo("Password is not valid.")
    }

    @Test
    fun `isPasswordValid should return true when password is valid`() {
        val (valid, message) = InputValidation.isPasswordValid("P@ssw0rd!")
        assertThat(valid).isTrue()
        assertThat(message).isEqualTo("")
    }
}