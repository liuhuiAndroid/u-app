package com.study.u.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.study.u.R
import com.study.u.data.Product
import com.study.u.inter.OnItemClickListener
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var mProductList: List<Product> = arrayListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.btnAddOrder.setOnClickListener {
                onItemClickListener?.onClick(it, layoutPosition)
            }
        }
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.title.text =
            "期限${mProductList[position].time_desc}，盈利${mProductList[position].gain}%"
        holder.itemView.address.text = "条件：${mProductList[position].gainCondition}U起"
        holder.itemView.price.text = "盈利${mProductList[position].gain}%"
    }

    private var mRecyclerView: RecyclerView? = null

    /**
     * 将RecycleView附加到Adapter上
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    /**
     * 将RecycleView从Adapter解除
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }
}