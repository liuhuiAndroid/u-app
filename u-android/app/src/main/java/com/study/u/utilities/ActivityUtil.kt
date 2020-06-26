package com.study.u.utilities

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.study.u.R

fun FragmentActivity.snackInfo(@StringRes id: Int) {
    Snackbar.make(findViewById(android.R.id.content), id, Snackbar.LENGTH_SHORT).show()
}

/**
 * 关闭软键盘
 */
fun FragmentActivity.hideKeyboard() {
    val view: View = findViewById(android.R.id.content)
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

/**
 * Error dialog
 */
fun FragmentActivity.errorDialog(@StringRes id: Int, listener: DialogInterface.OnClickListener? = null) =
    errorDialog(getString(id), listener)

/**
 * Error dialog
 */
fun FragmentActivity.errorDialog(
    message: CharSequence,
    listener: DialogInterface.OnClickListener? = null
) {
    MaterialAlertDialogBuilder(this)
        .setMessage(message)
        .setPositiveButton(R.string.dialog_confirm, listener)
        .create()
        .show()
}
