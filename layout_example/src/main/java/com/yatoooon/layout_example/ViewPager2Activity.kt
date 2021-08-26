package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yatoooon.coordinator_example.R
import kotlinx.android.synthetic.main.activity_tablayout.*
import kotlinx.android.synthetic.main.activity_view_pager2.*
import kotlinx.android.synthetic.main.item_page.view.*

class ViewPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        view_pager2.adapter = ViewPagerAdapter()
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(tab_layout, view_pager2) { tab, position ->
            tab.text = "position$position"
        }.attach()

    }
}

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {

    //array of colors to change the background color of screen
    private val colors = intArrayOf(
        android.R.color.black,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    //get the size of color array
    override fun getItemCount(): Int = 10

    //binding the screen with view
    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        tvTitle.text = "position$position"
        container.setBackgroundResource(colors[position % 4])
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)