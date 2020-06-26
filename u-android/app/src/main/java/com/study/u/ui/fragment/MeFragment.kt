package com.study.u.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.study.u.R
import com.study.u.ui.ModifyPasswordActivity
import com.study.u.viewmodel.MeViewModel
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel = ViewModelProvider(this)[MeViewModel::class.java]
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTvUsername.text = "用户名"

        // 修改密码
        mClModifyPassword.setOnClickListener {
            startActivity(Intent(activity, ModifyPasswordActivity::class.java))
        }
    }

}