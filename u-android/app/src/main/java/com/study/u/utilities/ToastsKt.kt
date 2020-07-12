package com.study.u.utilities

import android.widget.Toast
import com.study.u.AppContext

inline fun toast(message: Int): Toast = Toast
    .makeText(AppContext, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

inline fun toast(message: CharSequence): Toast = Toast
    .makeText(AppContext, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }