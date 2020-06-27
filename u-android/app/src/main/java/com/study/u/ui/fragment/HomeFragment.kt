package com.study.u.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.study.u.R
import com.study.u.adapter.ProductAdapter
import com.study.u.inter.OnItemClickListener
import com.study.u.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mProductAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getProductListLiveData()?.observe(viewLifecycleOwner, Observer {
            Timber.i("list: $it")

            it?.let {
                mProductAdapter.mProductList = it
                mProductAdapter.notifyDataSetChanged()
            }
        })

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mProductAdapter = ProductAdapter()
        mProductAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                val productId = mProductAdapter.mProductList[position].id
                homeViewModel.getOrderLiveData(productId)?.observe(viewLifecycleOwner, Observer {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                })
            }
        })
        mRecyclerView.adapter = mProductAdapter
        // 添加分割线
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}