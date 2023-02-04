package com.example.firebase_blog.util

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import com.example.firebase_blog.R

/**
 * A custom Dialog class to display loading spinner during background operations.
 */
class LoadingDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.loading_dialog)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)
        setCancelable(false)
    }
}