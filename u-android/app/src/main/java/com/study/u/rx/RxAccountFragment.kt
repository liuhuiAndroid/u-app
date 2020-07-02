package com.study.u.rx

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.study.u.ext.decode
import com.study.u.ui.LoginActivity
import com.study.u.utilities.MMKVConstants.TOKEN
import com.study.u.utilities.REQ_TOKEN
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers

class RxAccountFragment : Fragment() {

    companion object {
        const val TAG = "RxAccountFragment"
    }

    private val publishProcessor: PublishProcessor<Boolean> = PublishProcessor.create()

    fun check(): Flowable<Boolean> {
        return Flowable.just(decode(TOKEN, ""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                if (!TextUtils.isEmpty(it)) {
                    Flowable.just(true)
                } else {
                    publishProcessor.doOnSubscribe {
                        startActivityForResult(
                            Intent(activity, LoginActivity::class.java),
                            REQ_TOKEN
                        )
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        publishProcessor.apply {
            onNext(resultCode == Activity.RESULT_OK && requestCode == REQ_TOKEN)
            onComplete()
        }
    }

}