package com.omralcorut.demofeed.models

import com.omralcorut.demofeed.ui.home.HomeViewModel

data class LoadingModel(
    val loading: HomeViewModel.Loading,
    val isListEmpty: Boolean,
)