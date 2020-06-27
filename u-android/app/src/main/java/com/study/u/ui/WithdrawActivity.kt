package com.study.u.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.widget.textChanges
import com.study.u.R
import com.study.u.ext.debounceClick
import com.study.u.utilities.errorDialog
import com.study.u.utilities.hideKeyboard
import com.study.u.utilities.snackInfo
import com.study.u.viewmodel.AssetViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_withdraw.*

class WithdrawActivity : BaseActivity() {

    private lateinit var assetViewModel: AssetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]

        initToolbar(R.string.asset_withdraw)

        Observables.combineLatest(
            mTextInputAddress.editText!!.textChanges(),
            mTextInputNumber.editText!!.textChanges()
        ) { u, p ->
            u.isNotEmpty() && p.isNotEmpty()
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                withdrawButton.isEnabled = it
            })

        assetViewModel.subscribe().observe(this, Observer {
            assetViewModel.withdraw(
                mTextInputAddress.editText!!.text.toString(),
                mTextInputNumber.editText!!.text.toString().toInt()
            )
        })

        assetViewModel.apply {
            subscribeWithdraw().observe(this@WithdrawActivity, Observer {
                cancelLoadingDialog()
                // 提现成功
                snackInfo(R.string.asset_withdraw_success)
            })

            fail().observe(this@WithdrawActivity, Observer {
                cancelLoadingDialog()
                if (it.msg != null) {
                    errorDialog(it.msg)
                } else {
                    errorDialog(R.string.dialog_error_admin)
                }
            })
        }

        withdrawButton.debounceClick {
            hideKeyboard()
            showLoadingDialog(true, R.string.asset_withdraw_dialog)
            assetViewModel.setValue()
        }

    }

}