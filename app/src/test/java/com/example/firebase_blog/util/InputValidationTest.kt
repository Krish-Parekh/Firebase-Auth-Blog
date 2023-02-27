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

    @Test
    fun `isPhoneNumberValid should return true for valid mobile number`() {
        val (isValid, errorMessage) = InputValidation.isPhoneNumberValid("9876543210")
        assertThat(isValid).isTrue()
        assertThat(errorMessage).isEmpty()
    }

    @Test
    fun `isPhoneNumberValid should return false for empty mobile number`() {
        val (isValid, errorMessage) = InputValidation.isPhoneNumberValid("")
        assertThat(isValid).isFalse()
        assertThat(errorMessage).isEqualTo("Mobile number cannot be empty.")
    }

    @Test
    fun `isPhoneNumberValid should return false for mobile number not being 13 characters long`() {
        val (isValid, errorMessage) = InputValidation.isPhoneNumberValid("98765432")
        assertThat(isValid).isFalse()
        assertThat(errorMessage).isEqualTo("Mobile number must be 10 characters.")
    }

    @Test
    fun `isPhoneNumberValid should return false for mobile number containing non-digit characters`() {
        val (isValid, errorMessage) = InputValidation.isPhoneNumberValid("9876543a01")
        assertThat(isValid).isFalse()
        assertThat(errorMessage).isEqualTo("Mobile number can only contain digits.")
    }

    @Test
    fun `isPhoneNumberValid should return false for mobile number starting with 000`() {
        val (isValid, errorMessage) = InputValidation.isPhoneNumberValid("0007654321")
        assertThat(isValid).isFalse()
        assertThat(errorMessage).isEqualTo("Mobile number cannot start with 000.")
    }

    @Test
    fun `isMobileNumberValid should return false for mobile number with all digits same`() {
        val (isValid, errorMessage) = InputValidation.isPhoneNumberValid("1111111111")
        assertThat(isValid).isFalse()
        assertThat(errorMessage).isEqualTo("All the digits in mobile number cannot be same.")
    }
}