package com.yatoooon.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yatoooon.viewbinding.databinding.ResultProfileBinding

class MainFragment: Fragment() {

    private var _binding: ResultProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = "yatoooon"
        binding.btConfirm.setOnClickListener { println("onClick") }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}