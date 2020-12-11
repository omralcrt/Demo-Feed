package com.omralcorut.demofeed.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omralcorut.demofeed.R
import com.omralcorut.demofeed.databinding.FragmentHomeBinding
import com.omralcorut.demofeed.models.Mention
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
        setRecyclerViewScroll()
        viewModelObserver()
        return binding.root
    }

    private fun setAdapter() {
        homeEpoxyController =
            HomeEpoxyController(object : HomeEpoxyController.HomeEpoxyAdapterCallbacks {
                override fun feedMentionsClick(mentions: List<Mention>) {
                    val bundle = bundleOf("list" to mentions.toTypedArray())
                    findNavController().navigate(R.id.action_feed_to_mentionBs, bundle)
                }
            })

        homeEpoxyController.isDebugLoggingEnabled = true
        binding.recyclerViewFeed.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFeed.adapter = homeEpoxyController.adapter
    }

    private fun viewModelObserver() {
        homeViewModel.featureDetails.observe(viewLifecycleOwner, {
            homeEpoxyController.setData(homeViewModel.featureDetails.value,
                homeViewModel.timelineDetails.value,
                homeViewModel.loadingModel.value?.loading == HomeViewModel.Loading.LOADING)
            binding.recyclerViewFeed.layoutManager?.scrollToPosition(0)
        })

        homeViewModel.loadingModel.observe(viewLifecycleOwner, {
            homeEpoxyController.setData(
                homeViewModel.featureDetails.value,
                homeViewModel.timelineDetails.value,
                it.loading == HomeViewModel.Loading.LOADING
            )
        })
    }

    private fun setRecyclerViewScroll() {
        binding.recyclerViewFeed.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManger = binding.recyclerViewFeed.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManger.childCount
                val totalItemCount = layoutManger.itemCount
                val firstVisibleItemPosition = layoutManger.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    homeViewModel.fetchNextPage()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}