package com.study.u.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.study.u.R
import com.study.u.viewmodel.LoadingDialogViewModel
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog : DialogFragment() {

    companion object {
        const val MESSAGE = "MESSAGE"
        const val TAG = "LoadingDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
        arguments?.let {
            val megResId = it.getInt(MESSAGE, R.string.dialog_loading)
            messageTextView.setText(megResId)
        }
    }
}