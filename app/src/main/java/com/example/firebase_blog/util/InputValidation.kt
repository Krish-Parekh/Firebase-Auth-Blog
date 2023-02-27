package com.example.firebase_blog.util

/**
 * InputValidation is a class that contains functions for validating user inputs such as email and password.
 */
class InputValidation {
    companion object {
        /**
         * EMAIL_ADDRESS_PATTERN is a regular expression used to validate the email address format.
         */
        private const val EMAIL_ADDRESS_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"

        /**
         * PASSWORD_PATTERN is the regular expression that is used to check the following constraints on the password:
         * 1. Password should contain at least 4 characters
         * 2. Password should contain at least one letter
         * 3. Password should contain at least one digit
         * 4. Password should contain at least one special character from the specified set of characters
         */
        private const val PASSWORD_PATTERN = "^.*(?=.{4,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!&\$%&?#_@ ]).*\$"

        /**
         * checkNullity is a function that checks if the input string is not empty.
         *
         * @param input: The input string to be checked.
         * @return True if the input is not empty, false otherwise.
         */
        fun checkNullity(input: String): Boolean {
            return input.isNotEmpty();
        }

        /**
         * The function isUsernameValid checks if the provided username is valid based on the following constraints:
         * 1. The username cannot be empty.
         * 2. The length of the username cannot be more than 100 characters.
         * 3. The username cannot start with a number.
         * 4. The username can only contain alphabets and numbers.
         *
         * @param username The username string to be validated.
         * @return A pair of Boolean and String where the Boolean indicates if the username is valid or not,
         * and the String provides a message indicating why the username is not valid if the Boolean is false.
         */
        fun isUsernameValid(username: String): Pair<Boolean, String> {
            if (username.isEmpty()) {
                return Pair(false, "Username cannot be empty.")
            }
            if (username.length > 100) {
                return Pair(false, "Username cannot be more than 100 characters.")
            }
            if (username[0].isDigit()) {
                return Pair(false, "Username cannot start with a number.")
            }
            if (username.matches("^[a-zA-Z0-9 ]+$".toRegex()).not()) {
                return Pair(false, "Username can only contain alphabets and numbers.")
            }
            return Pair(true, "")
        }

        /**
         * isEmailValid is a function that validates an email address.
         *
         * @param email: The email address to be validated.
         * @return A pair containing a boolean value indicating the validation result and a string message describing the result.
         */
        fun isEmailValid(email: String): Pair<Boolean, String> {
            if (email.isEmpty()) {
                return Pair(false, "Email cannot be empty.")
            }
            if (email[0].isDigit()) {
                return Pair(false, "Email cannot start with a number.")
            }
            if (EMAIL_ADDRESS_PATTERN.toRegex().matches(email).not()) {
                return Pair(false, "Email is not valid.")
            }
            return Pair(true, "")
        }

        /**
         * isPasswordValid is a function that validates a password.
         *
         * @param password: The password to be validated.
         * @return A pair containing a boolean value indicating the validation result and a string message describing the result.
         */
        fun isPasswordValid(password: String): Pair<Boolean, String> {
            if (password.isEmpty()) {
                return Pair(false, "Password cannot be empty.")
            }
            if (PASSWORD_PATTERN.toRegex().matches(password).not()) {
                return Pair(false, "Password is not valid.")
            }
            return Pair(true, "")
        }

        fun isPhoneNumberValid(phoneNumber: String): Pair<Boolean, String> {
            if (phoneNumber.isEmpty()) {
                return Pair(false, "Mobile number cannot be empty.")
            }
            if (phoneNumber.length != 10) {
                return Pair(false, "Mobile number must be 10 characters.")
            }
            if (phoneNumber.substring(0, 3).equals("000")) {
                return Pair(false, "Mobile number cannot start with 000.")
            }
            val firstDigit = phoneNumber[0]
            if (phoneNumber.slice(0..9).all { it == firstDigit }) {
                return Pair(false, "All the digits in mobile number cannot be same.")
            }
            if (phoneNumber.matches("^[0-9]+$".toRegex()).not()) {
                return Pair(false, "Mobile number can only contain digits.")
            }
            return Pair(true, "")
        }
    }
}
