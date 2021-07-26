package com.yatoooon.fragment_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import kotlinx.android.synthetic.main.fragment_afteracademy.*
import kotlinx.android.synthetic.main.fragment_afteracademy.tv_like_count
import kotlinx.android.synthetic.main.fragment_user.*

class BottomNavigationActivity : AppCompatActivity() {
    private val mindOrksFragment = MindOrksFragment()
    private val afterAcademyFragment = AfterAcademyFragment()
    private val userFragment = UserFragment()
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = mindOrksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        fragmentManager.beginTransaction().apply {
            add(R.id.container, userFragment, getString(R.string.user)).hide(userFragment)
            add(R.id.container, afterAcademyFragment, getString(R.string.after_academy)).hide(
                afterAcademyFragment
            )
            add(R.id.container, mindOrksFragment, getString(R.string.mindOrks))
        }.commit()
        initListeners()
        bottomNavigationView.itemIconTintList = null
    }

    private fun initListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_mindorks -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(mindOrksFragment)
                        .commit()
                    activeFragment = mindOrksFragment
                    true
                }

                R.id.navigation_afterAcademy -> {
                    fragmentManager.beginTransaction().hide(activeFragment)
                        .show(afterAcademyFragment).commit()
                    activeFragment = afterAcademyFragment
                    true
                }

                R.id.navigation_user -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(userFragment)
                        .commit()
                    activeFragment = userFragment
                    true
                }

                else -> false
            }
        }
    }
}

class AfterAcademyFragment : Fragment() {
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_afteracademy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        like_logo.setOnClickListener {
            count++
            tv_like_count.text = "$count"
        }
    }
}

class MindOrksFragment : Fragment() {
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mindorks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        like_logo.setOnClickListener {
            count++
            tv_like_count.text = "$count"
        }
    }
}

class UserFragment : Fragment() {
    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        click_logo.setOnClickListener {
            count++
            tv_like_count.text = "$count"
        }
    }
}