package com.study.u.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.study.u.R
import com.study.u.ui.WithdrawActivity
import com.study.u.viewmodel.AssetViewModel
import kotlinx.android.synthetic.main.fragment_asset.*
import timber.log.Timber

class AssetFragment : Fragment() {

    private lateinit var assetViewModel: AssetViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assetViewModel = ViewModelProvider(this)[AssetViewModel::class.java]
        return inflater.inflate(R.layout.fragment_asset, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assetViewModel.subscribeAsset()?.observe(viewLifecycleOwner, Observer {
            Timber.i("asset: $it")

            it?.let {
                mTvAsset.text = "投资资产：${it.invest}U \n推广资产：${it.extend}U \n总资产：${it.allUsdt}U "
            }
        })

        withdrawButton.setOnClickListener {
            startActivity(Intent(activity, WithdrawActivity::class.java))
        }
    }

}