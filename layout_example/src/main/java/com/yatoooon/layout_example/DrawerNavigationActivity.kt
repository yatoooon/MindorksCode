package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.yatoooon.coordinator_example.R
import kotlinx.android.synthetic.main.activity_drawer_navigation.*

class DrawerNavigationActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_navigation)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener {
            if (drawer_layout.isDrawerOpen(navigationView)) {
                drawer_layout.closeDrawer(navigationView)
            } else {
                drawer_layout.openDrawer(navigationView)
            }
        }


    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_public -> {
                Toast.makeText(this, "Publication", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_store -> {
                Toast.makeText(this, "Android Store", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_news -> {
                Toast.makeText(this, "Newsletter", Toast.LENGTH_SHORT).show()
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}