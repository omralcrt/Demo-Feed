package com.omralcorut.demofeed.ui.home

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.Carousel.setDefaultGlobalSnapHelperFactory
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController

class HomeEpoxyController : TypedEpoxyController<ArrayList<HomeEpoxyModel>>() {

    override fun buildModels(data: ArrayList<HomeEpoxyModel>?) {
        data?.forEach { homeEpoxyModel ->
            when (homeEpoxyModel.type) {
                HomeEpoxyType.Feature -> {
                    val featureEpoxyModelList: ArrayList<FeatureEpoxyModel> = ArrayList()
                    homeEpoxyModel as HomeFeatureEpoxyModel
                    if (homeEpoxyModel.featured?.featured?.isNotEmpty() == true) {
                        FeatureTitleEpoxyModel_()
                            .id("title")
                            .addTo(this)
                    }
                    homeEpoxyModel.featured?.featured?.forEach {
                        featureEpoxyModelList.add(
                            FeatureEpoxyModel_()
                                .id(it.id)
                                .featureDetail(it)
                        )
                    }
                    setDefaultGlobalSnapHelperFactory(null)

                    CarouselModel_()
                        .id(homeEpoxyModel.hashCode())
                        .models(featureEpoxyModelList)
                        .padding(Carousel.Padding.dp(20,0,20,16,8))
                        .addTo(this)
                }
                HomeEpoxyType.Feed -> {
                    homeEpoxyModel as HomeFeedEpoxyModel
                    homeEpoxyModel.timeline?.timeline?.forEach {
                        FeedEpoxyModel_()
                            .id(it.id)
                            .timelineDetail(it)
                            .addTo(this)
                    }
                }
            }
        }
    }
}