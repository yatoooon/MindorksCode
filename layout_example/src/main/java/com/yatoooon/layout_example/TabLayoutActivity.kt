package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.yatoooon.coordinator_example.R
import kotlinx.android.synthetic.main.activity_tablayout.*

class TabLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)
        setSupportActionBar(toolbar)

        viewPager2.adapter = ViewPagerAdapter()
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            tab.text = "TAB ${(position + 1)}"
        }.attach()


    }


}