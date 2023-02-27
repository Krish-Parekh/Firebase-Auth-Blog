package com.example.firebase_blog.util


import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import com.example.firebase_blog.R
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


fun TextInputEditText.handleKeyEvent(previousView: TextInputEditText?) {
    setOnKeyListener { _, keyCode, event ->
        if (
            event!!.action == KeyEvent.ACTION_DOWN &&
            keyCode == KeyEvent.KEYCODE_DEL &&
            id != R.id.etOtpCode1 &&
            text!!.isEmpty()
        ) {
            previousView!!.text = null
            previousView.requestFocus()
            return@setOnKeyListener true
        }
        return@setOnKeyListener false
    }
}

fun TextInputEditText.handleTextChange(nextView: TextInputEditText?) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editText: Editable?) {
            val text = editText.toString()
            when (id) {
                R.id.etOtpCode1,
                R.id.etOtpCode2,
                R.id.etOtpCode3,
                R.id.etOtpCode4,
                R.id.etOtpCode5 -> if (text.length == 1) nextView?.requestFocus()
            }
        }
    })
}