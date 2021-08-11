package com.yatoooon.architecture.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yatoooon.architecture.R
import com.yatoooon.architecture.databinding.MainFragmentBinding
import com.yatoooon.architecture.ui.viewmodel.MainViewModel
import com.yatoooon.architecture.ui.adapter.FooterAdapter
import com.yatoooon.architecture.ui.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    // This property is only valid between onCreateView and onDestroyView

    private val viewModel: MainViewModel by viewModels()

    private val repoAdapter = RepoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
        setUpListener()
    }

    private fun setUpView() {
        binding.srlRefresh.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        binding.srlRefresh.setOnRefreshListener {
            repoAdapter.refresh()
            viewModel.isRefreshing.value = true
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter =
            repoAdapter.withLoadStateFooter(FooterAdapter { repoAdapter.retry() })
    }

    private fun setUpViewModel() {
        lifecycleScope.launch {
            viewModel.pagingData.collect { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
        viewModel.isRefreshing.observe(viewLifecycleOwner, Observer { boolean ->
            binding.srlRefresh.isRefreshing = boolean
        })
    }

    private fun setUpListener() {
        repoAdapter.setOnClickListener { item, view ->
            val action = MainFragmentDirections.actionMainFragmentToMainDetailFragment(
                item.html_url,
                item.name
            )
            Navigation.findNavController(view).navigate(action)
        }
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    viewModel.isRefreshing.value = false
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.srlRefresh.isEnabled = true
                }
                is LoadState.Loading -> {
                    if (!binding.srlRefresh.isRefreshing) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.srlRefresh.isEnabled = false
                    }
                }
                is LoadState.Error -> {
                    viewModel.isRefreshing.value = false
                    val state = it.refresh as LoadState.Error
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.srlRefresh.isEnabled = true
                    Toast.makeText(
                        context,
                        "Load Error: ${state.error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
