package com.yatoooon.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yatoooon.viewbinding.databinding.ResultProfileBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ResultProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tvName.text = "yatoooon"
        binding.btConfirm.setOnClickListener { println("onClick") }

    }
}
