package com.study.u.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.widget.textChanges
import com.study.u.R
import com.study.u.ext.debounceClick
import com.study.u.ext.encodeKV
import com.study.u.utilities.MMKVConstants
import com.study.u.utilities.errorDialog
import com.study.u.utilities.hideKeyboard
import com.study.u.utilities.snackInfo
import com.study.u.viewmodel.AccountViewModel
import com.study.u.viewmodel.LoadingDialogViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_register.mTextInputAccount
import kotlinx.android.synthetic.main.activity_register.mTextInputPassword
import kotlinx.android.synthetic.main.activity_register.registerButton

class RegisterActivity : BaseActivity() {

    private lateinit var loadingDialogViewModel: LoadingDialogViewModel
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loadingDialogViewModel = ViewModelProvider(this)[LoadingDialogViewModel::class.java]
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        initToolbar(R.string.register_register)

        mTextInputAccount.editText!!.textChanges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                mTextInputAccount.error =
                    if (it.isEmpty() || it.length > 5) null else getString(R.string.login_account_input_error)
            })

        mTextInputPassword.editText!!.textChanges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                mTextInputPassword.error =
                    if (it.isEmpty() || it.length > 5) null else getString(R.string.login_password_input_error)
            })

        Observables.combineLatest(
            mTextInputAccount.editText!!.textChanges(),
            mTextInputPassword.editText!!.textChanges()
        ) { u, p ->
            u.length > 5 && p.length > 5
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                registerButton.isEnabled = it
            })

        loadingDialogViewModel.subscribe().observe(this, Observer {
            accountViewModel.register(
                mTextInputAccount.editText!!.text.toString(),
                mTextInputPassword.editText!!.text.toString()
            )
        })

        accountViewModel.apply {
            subscribeLogin().observe(this@RegisterActivity, Observer {
                cancelLoadingDialog()
                // 注册成功
                snackInfo(R.string.register_success)
                finish()
            })

            fail().observe(this@RegisterActivity, Observer {
                cancelLoadingDialog()
                if (it.msg != null) {
                    errorDialog(it.msg)
                } else {
                    errorDialog(R.string.dialog_error_admin)
                }
            })
        }

        registerButton.debounceClick {
            hideKeyboard()
            showLoadingDialog(true, R.string.register_dialog)
            loadingDialogViewModel.setValue()
        }
    }

}