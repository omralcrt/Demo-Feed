package com.omralcorut.demofeed.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.omralcorut.demofeed.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var homeEpoxyController: HomeEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setAdapter()
        viewModelObserver()
        return binding.root
    }

    private fun setAdapter() {
        homeEpoxyController = HomeEpoxyController()

        homeEpoxyController.isDebugLoggingEnabled = true
        binding.recyclerViewFeed.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFeed.adapter = homeEpoxyController.adapter
    }

    private fun viewModelObserver() {
        homeViewModel.feedList.observe(viewLifecycleOwner, {
            homeEpoxyController.setData(it)
            binding.recyclerViewFeed.layoutManager?.scrollToPosition( 0)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}