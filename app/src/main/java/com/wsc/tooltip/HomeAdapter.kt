package com.wsc.tooltip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class HomeAdapter(
    private val mContext: Context,
    private val mValues: ArrayList<Item>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val root: View
        private val imageView1: ImageView
        private val imageView2: ImageView
        private val imageView3: ImageView
        private var item: Item? = null

        init {
            root = v.findViewById(R.id.root)
            imageView1 = v.findViewById(R.id.imageView1)
            imageView2 = v.findViewById(R.id.imageView2)
            imageView3 = v.findViewById(R.id.imageView3)
        }

        fun setData(item: Item) {
            this.item = item
            imageView1.setOnClickListener { view ->
                TooltipWindow(view.context).showToolTip(
                    view,
                    root
                )
            }

            imageView2.setOnClickListener { view ->
                TooltipWindow(view.context).showToolTip(
                    view,
                    root
                )
            }

            imageView3.setOnClickListener { view ->
                TooltipWindow(view.context).showToolTip(
                    view,
                    root
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setData(mValues[position])
    }

    override fun getItemCount(): Int {
        return mValues.size
    }
}
