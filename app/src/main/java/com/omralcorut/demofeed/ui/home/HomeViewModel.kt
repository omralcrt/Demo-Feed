package com.omralcorut.demofeed.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omralcorut.demofeed.models.FeatureDetail
import com.omralcorut.demofeed.models.LoadingModel
import com.omralcorut.demofeed.models.TimelineDetail
import com.omralcorut.demofeed.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    var featureDetails: MutableLiveData<ArrayList<FeatureDetail>> = MutableLiveData(arrayListOf())
    var timelineDetails: MutableLiveData<ArrayList<TimelineDetail>> = MutableLiveData(arrayListOf())
    private var _canNextPageLoaded = true
    private var pageNumber: Int = 1
    val loadingModel: MutableLiveData<LoadingModel> = MutableLiveData()

    init {
        fetchFeatures()
        fetchTimeline()
    }

    private fun fetchFeatures() {
        viewModelScope.launch {
            val featured = homeRepository.getFeatures()!!
            featureDetails.value = ArrayList(featured.featured)
        }
    }

    private fun fetchTimeline() {
        pageNumber = 1
        val list = timelineDetails.value
        list?.clear()
        timelineDetails.value = list
        if (!isLoading()) {
            updateLoadingState(loading = Loading.LOADING, e = null, isListEmpty = isListEmpty())
            viewModelScope.launch {
                val timeline = homeRepository.getTimeline(pageNumber)!!
                timelineDetails.value = ArrayList(timeline.timeline)
                if (timeline.timeline.isNullOrEmpty()) {
                    _canNextPageLoaded = false
                }
                pageNumber++
                updateLoadingState(loading = Loading.COMPLETED, e = null, isListEmpty = isListEmpty())
            }
        }
    }

    fun fetchNextPage() {
        if (_canNextPageLoaded && !isLoading()) {
            updateLoadingState(loading = Loading.LOADING, e = null, isListEmpty = isListEmpty())
            viewModelScope.launch {
                val timeline = homeRepository.getTimeline(pageNumber)!!
                val updatedList = timelineDetails.value
                updatedList?.addAll(timeline.timeline)
                timelineDetails.value = updatedList
                if (timeline.timeline.isNullOrEmpty()) {
                    _canNextPageLoaded = false
                }
                pageNumber++
                updateLoadingState(loading = Loading.COMPLETED, e = null, isListEmpty = isListEmpty())
            }
        }
    }

    private fun isListEmpty(): Boolean{
        return timelineDetails.value.isNullOrEmpty()
    }

    private fun isLoading(): Boolean{
        loadingModel.value?.let {
            return it.loading == Loading.LOADING
        } ?: return false
    }

    private fun updateLoadingState(loading: Loading, e: Throwable?, isListEmpty: Boolean = true){
        loadingModel.value = LoadingModel(
            loading = loading,
            isListEmpty = isListEmpty
        )

    }
    enum class Loading{
        LOADING, COMPLETED, ERROR
    }
}