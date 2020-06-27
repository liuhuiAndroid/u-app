package com.study.u.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jakewharton.rxbinding4.widget.textChanges
import com.study.u.R
import com.study.u.ext.debounceClick
import com.study.u.utilities.errorDialog
import com.study.u.utilities.hideKeyboard
import com.study.u.utilities.snackInfo
import com.study.u.viewmodel.AccountViewModel
import com.study.u.viewmodel.LoadingDialogViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_modify_password.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModifyPasswordActivity : BaseActivity() {

    private lateinit var loadingDialogViewModel: LoadingDialogViewModel
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_password)

        loadingDialogViewModel = ViewModelProvider(this)[LoadingDialogViewModel::class.java]
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        initToolbar(R.string.modify_password)

        mTextInputOldPassword.editText!!.textChanges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                mTextInputOldPassword.error =
                    if (it.isEmpty() || it.length > 5) null else getString(R.string.login_password_input_error)
            })

        mTextInputNewPassword.editText!!.textChanges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                mTextInputNewPassword.error =
                    if (it.isEmpty() || it.length > 5) null else getString(R.string.login_password_input_error)
            })

        mTextInputConfirmPassword.editText!!.textChanges()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                mTextInputConfirmPassword.error =
                    if (it.isEmpty() || it.length > 5) {
                        if(it.toString() != mTextInputNewPassword.editText!!.text.toString()){
                            getString(R.string.modify_password_new_error)
                        }else{
                            null
                        }
                    } else {
                        getString(R.string.login_password_input_error)
                    }
            })

        Observables.combineLatest(
            mTextInputOldPassword.editText!!.textChanges(),
            mTextInputNewPassword.editText!!.textChanges(),
            mTextInputConfirmPassword.editText!!.textChanges()
        ) { u, p, q ->
            u.length > 5 && p.length > 5 && q.length > 5 && p.toString() == q.toString()
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                modifyButton.isEnabled = it
            })

        loadingDialogViewModel.subscribe().observe(this, Observer {
            accountViewModel.modifyPassword(
                mTextInputOldPassword.editText!!.text.toString(),
                mTextInputNewPassword.editText!!.text.toString()
            )
        })

        accountViewModel.apply {
            subscribeModifyPassword().observe(this@ModifyPasswordActivity, Observer {
                cancelLoadingDialog()
                // 修改密码成功
                snackInfo(R.string.modify_password_success)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        delay(1000)
                    }
                    finish()
                }
            })

            fail().observe(this@ModifyPasswordActivity, Observer {
                cancelLoadingDialog()
                if (it.msg != null) {
                    errorDialog(it.msg)
                } else {
                    errorDialog(R.string.dialog_error_admin)
                }
            })
        }

        modifyButton.debounceClick {
            hideKeyboard()
            showLoadingDialog(true, R.string.modify_password_dialog)
            loadingDialogViewModel.setValue()
        }
    }

}