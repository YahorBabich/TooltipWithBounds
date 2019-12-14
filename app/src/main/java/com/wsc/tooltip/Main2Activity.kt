package com.wsc.tooltip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val arrayList = ArrayList<Item>()

        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))
        arrayList.add(Item("Item 1"))


        val adapter = HomeAdapter(this, arrayList)
        recyclerView.adapter = adapter

        /**
         * AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         */

        //AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        //recyclerView.setLayoutManager(layoutManager);


        /**
         * Simple GridLayoutManager that spans two columns
         */
        val manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(manager)
    }

}
