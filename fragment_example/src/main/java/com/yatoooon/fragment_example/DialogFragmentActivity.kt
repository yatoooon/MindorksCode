package com.yatoooon.fragment_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_dialog_fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class DialogFragmentActivity : AppCompatActivity() {
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_fragment)
        btnSimpleDialog.setOnClickListener {
            SimpleDialog.newInstance(
                getString(R.string.label_logout),
                getString(R.string.msg_logout)
            ).show(supportFragmentManager, SimpleDialog.TAG)
        }
        btnSimpleDialog2.setOnClickListener {
            SimpleDialog2.newInstance().show(supportFragmentManager, SimpleDialog2.TAG)
        }



        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        sharedViewModel.name.observe(this, Observer {
            tvName.text = it
        })
        btnDataDialog.setOnClickListener {
            DialogWithData().show(supportFragmentManager, DialogWithData.TAG)
        }
    }
}