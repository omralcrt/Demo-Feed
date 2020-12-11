package com.omralcorut.demofeed.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omralcorut.demofeed.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    var feedList : MutableLiveData<ArrayList<HomeEpoxyModel>> = MutableLiveData(arrayListOf(
        HomeFeatureEpoxyModel(),
        HomeFeedEpoxyModel()
    ))

    init {
        fetchFeatures()
        fetchTimeline()
    }

    private fun fetchFeatures() {
        viewModelScope.launch {
            val featured = homeRepository.getFeatures()!!
            val homeFeatureEpoxyModel = HomeFeatureEpoxyModel(
                featured = featured
            )
            val newList = feedList.value!!
            newList[0] = homeFeatureEpoxyModel
            feedList.value = newList
        }
    }

    private fun fetchTimeline() {
        viewModelScope.launch {
            val timeline = homeRepository.getTimeline()!!
            val homeFeedEpoxyModel = HomeFeedEpoxyModel(
                timeline = timeline
            )
            val newList = feedList.value!!
            newList[1] = homeFeedEpoxyModel
            feedList.value = newList
        }
    }
}