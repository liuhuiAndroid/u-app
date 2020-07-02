package com.study.u.rx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import io.reactivex.rxjava3.core.Flowable

class RxAccount constructor(private val fragmentManager: FragmentManager) {

    companion object {
        const val TAG = "RxAccount"
    }

    constructor(activity: FragmentActivity) : this(activity.supportFragmentManager)

    constructor(fragment: Fragment) : this(fragment.childFragmentManager)

    private val rxAccountFragment: RxAccountFragment = getRxAccountFragment(fragmentManager)

    fun check(): Flowable<Boolean> = rxAccountFragment.check().doAfterNext {
        fragmentManager.apply {
            beginTransaction()
                .remove(rxAccountFragment)
                .commitNow()
            executePendingTransactions()
        }
    }

    private fun getRxAccountFragment(fragmentManager: FragmentManager): RxAccountFragment {
        val fragment = findRxAccountFragment(fragmentManager)
        return if (fragment == null) {
            val rxAccountFragment = RxAccountFragment()
            fragmentManager.apply {
                beginTransaction()
                    .add(rxAccountFragment, TAG)
                    .commitNow()
                executePendingTransactions()
            }
            rxAccountFragment
        } else fragment
    }

    private fun findRxAccountFragment(fragmentManager: FragmentManager): RxAccountFragment? {
        val fragment = fragmentManager.findFragmentByTag(TAG)
        return if (fragment != null && fragment is RxAccountFragment) fragment else null
    }
}