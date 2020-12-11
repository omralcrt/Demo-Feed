package com.omralcorut.demofeed.ui.home

import com.airbnb.epoxy.*
import com.airbnb.epoxy.Carousel.setDefaultGlobalSnapHelperFactory
import com.omralcorut.demofeed.models.FeatureDetail
import com.omralcorut.demofeed.models.Mention
import com.omralcorut.demofeed.models.TimelineDetail

class HomeEpoxyController(
    var adapterCallbacks: HomeEpoxyAdapterCallbacks
) :
    Typed3EpoxyController<ArrayList<FeatureDetail>, ArrayList<TimelineDetail>, Boolean>() {

    override fun buildModels(
        data1: ArrayList<FeatureDetail>,
        data2: ArrayList<TimelineDetail>,
        data3: Boolean
    ) {
        val featureEpoxyModelList: ArrayList<FeatureEpoxyModel> = ArrayList()
        if (data1.isNotEmpty()) {
            FeatureTitleEpoxyModel_()
                .id("title")
                .addTo(this)
        }
        data1.forEach {
            featureEpoxyModelList.add(
                FeatureEpoxyModel_()
                    .id(it.id)
                    .featureDetail(it)
            )
        }
        setDefaultGlobalSnapHelperFactory(null)

        CarouselModel_()
            .id(data1.hashCode())
            .models(featureEpoxyModelList)
            .padding(Carousel.Padding.dp(20, 0, 20, 16, 8))
            .addTo(this)

        data2.forEach {
            FeedEpoxyModel_()
                .id(it.id)
                .timelineDetail(it)
                .clickListener { model, _, _, _ ->
                    adapterCallbacks.feedMentionsClick(mentions = model.timelineDetail()?.mentions!!)
                }
                .addTo(this)
        }

        val loadingMoreView =
            LoadMoreView_().apply { id(LoadMoreView::class.java.simpleName) }

        if (!data2.isNullOrEmpty()) {
            loadingMoreView.addIf(data3, this)
        }
    }

    interface HomeEpoxyAdapterCallbacks {
        fun feedMentionsClick(mentions: List<Mention>)
    }
}