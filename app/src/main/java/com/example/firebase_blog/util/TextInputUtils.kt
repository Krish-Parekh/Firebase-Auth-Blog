package com.example.firebase_blog.util

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Adds a text watcher to the TextInputLayout.
 * Clears the error message when text is entered into the EditText.
 */
fun TextInputLayout.addTextWatcher() {
    editText?.addTextChangedListener {
        error = null
    }
}


/**
 * Returns the value entered into the TextInputEditText as a string.
 * @return the string entered into the TextInputEditText after being trimmed.
 */
fun TextInputEditText.getValue(): String {
    return text.toString().trim()
}