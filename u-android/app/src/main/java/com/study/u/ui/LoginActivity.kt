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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loadingDialogViewModel: LoadingDialogViewModel
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadingDialogViewModel = ViewModelProvider(this)[LoadingDialogViewModel::class.java]
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        initToolbar()

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
                loginButton.isEnabled = it
            })

        loadingDialogViewModel.subscribe().observe(this, Observer {
            accountViewModel.login(
                mTextInputAccount.editText!!.text.toString(),
                mTextInputPassword.editText!!.text.toString()
            )
        })

        accountViewModel.apply {
            subscribeLogin().observe(this@LoginActivity, Observer {
                cancelLoadingDialog()
                // 登录成功
                encodeKV(MMKVConstants.TOKEN, it)
                snackInfo(R.string.login_success)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            })

            fail().observe(this@LoginActivity, Observer {
                cancelLoadingDialog()
                if (it.msg != null) {
                    errorDialog(it.msg)
                } else {
                    errorDialog(R.string.dialog_error_admin)
                }
            })
        }

        loginButton.debounceClick {
            hideKeyboard()
            showLoadingDialog(true, R.string.login_dialog)
            loadingDialogViewModel.setValue()
        }

        forgetPasswordButton.debounceClick {

        }

        registerButton.debounceClick {

        }
    }

}
