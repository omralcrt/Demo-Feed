package com.omralcorut.demofeed.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.*
import com.omralcorut.demofeed.R
import com.omralcorut.demofeed.models.FeatureDetail
import com.omralcorut.demofeed.models.TimelineDetail
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

@EpoxyModelClass(layout = R.layout.holder_feature_title)
abstract class FeatureTitleEpoxyModel : EpoxyModel<View>()

@EpoxyModelClass(layout = R.layout.holder_feature)
abstract class FeatureEpoxyModel : EpoxyModelWithHolder<FeatureEpoxyModel.Holder>() {

    @EpoxyAttribute
    var featureDetail: FeatureDetail? = null

    override fun bind(holder: Holder) {
        holder.photoImageView.load(featureDetail?.imageUrl)
        holder.cityTextView.text = featureDetail?.city
        holder.countryTextView.text = featureDetail?.country
    }

    class Holder : EpoxyHolder() {
        lateinit var itemView: View
        lateinit var photoImageView: ImageView
        lateinit var cityTextView: TextView
        lateinit var countryTextView: TextView

        override fun bindView(itemView: View) {
            this.itemView = itemView
            photoImageView = itemView.findViewById(R.id.image_view_photo)
            cityTextView = itemView.findViewById(R.id.text_view_city)
            countryTextView = itemView.findViewById(R.id.text_view_country)
        }
    }
}

@EpoxyModelClass(layout = R.layout.holder_feed)
abstract class FeedEpoxyModel : EpoxyModelWithHolder<FeedEpoxyModel.Holder>() {

    @EpoxyAttribute
    var timelineDetail: TimelineDetail? = null

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.photoImageView.load(timelineDetail?.imageUrl)
        holder.titleTextView.text = timelineDetail?.title
        holder.countryCountTextView.text = holder.itemView.resources.getString(
            R.string.feed_holder_country_count,
            timelineDetail?.countryCount
        )
        holder.dateTextView.text = PrettyTime(Locale.ENGLISH).format(Calendar.getInstance().apply {
            timeInMillis =
                timelineDetail?.date?.toLong()!!
        })
        holder.mentionLayout.setOnClickListener(clickListener)
        when (timelineDetail?.mentions?.size) {
            0 -> holder.mentionLayout.visibility = View.GONE
            1 -> {
                holder.mentionLayout.visibility = View.VISIBLE
                holder.firstUserImageView.visibility = View.VISIBLE
                holder.secondUserImageView.visibility = View.GONE
                holder.thirdUserImageView.visibility = View.GONE
                holder.moreUserTextView.visibility = View.GONE
                holder.firstUserImageView.load(timelineDetail?.mentions?.get(0)?.profileImage)
            }
            2 -> {
                holder.mentionLayout.visibility = View.VISIBLE
                holder.firstUserImageView.visibility = View.VISIBLE
                holder.secondUserImageView.visibility = View.VISIBLE
                holder.thirdUserImageView.visibility = View.GONE
                holder.moreUserTextView.visibility = View.GONE
                holder.firstUserImageView.load(timelineDetail?.mentions?.get(0)?.profileImage)
                holder.secondUserImageView.load(timelineDetail?.mentions?.get(1)?.profileImage)
            }
            3 -> {
                holder.mentionLayout.visibility = View.VISIBLE
                holder.firstUserImageView.visibility = View.VISIBLE
                holder.secondUserImageView.visibility = View.VISIBLE
                holder.thirdUserImageView.visibility = View.VISIBLE
                holder.moreUserTextView.visibility = View.GONE
                holder.firstUserImageView.load(timelineDetail?.mentions?.get(0)?.profileImage)
                holder.secondUserImageView.load(timelineDetail?.mentions?.get(1)?.profileImage)
                holder.thirdUserImageView.load(timelineDetail?.mentions?.get(2)?.profileImage)
            }
            else -> {
                holder.mentionLayout.visibility = View.VISIBLE
                holder.firstUserImageView.visibility = View.VISIBLE
                holder.secondUserImageView.visibility = View.VISIBLE
                holder.thirdUserImageView.visibility = View.VISIBLE
                holder.moreUserTextView.visibility = View.VISIBLE
                holder.firstUserImageView.load(timelineDetail?.mentions?.get(0)?.profileImage)
                holder.secondUserImageView.load(timelineDetail?.mentions?.get(1)?.profileImage)
                holder.thirdUserImageView.load(timelineDetail?.mentions?.get(2)?.profileImage)
                holder.moreUserTextView.text = holder.itemView.resources.getString(
                    R.string.feed_holder_more_mention_count,
                    timelineDetail?.mentions?.size?.minus(3)
                )
            }
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var itemView: View
        lateinit var photoImageView: ImageView
        lateinit var titleTextView: TextView
        lateinit var countryCountTextView: TextView
        lateinit var dateTextView: TextView
        lateinit var mentionLayout: ConstraintLayout
        lateinit var firstUserImageView: ImageView
        lateinit var secondUserImageView: ImageView
        lateinit var thirdUserImageView: ImageView
        lateinit var moreUserTextView: TextView

        override fun bindView(itemView: View) {
            this.itemView = itemView
            photoImageView = itemView.findViewById(R.id.image_view_photo)
            titleTextView = itemView.findViewById(R.id.text_view_title)
            countryCountTextView = itemView.findViewById(R.id.text_view_country_count)
            dateTextView = itemView.findViewById(R.id.text_view_date)
            mentionLayout = itemView.findViewById(R.id.layout_mention)
            firstUserImageView = itemView.findViewById(R.id.image_view_first_user)
            secondUserImageView = itemView.findViewById(R.id.image_view_second_user)
            thirdUserImageView = itemView.findViewById(R.id.image_view_third_user)
            moreUserTextView = itemView.findViewById(R.id.text_view_more_mention_count)
        }
    }
}

@EpoxyModelClass(layout = R.layout.holder_loading)
abstract class LoadMoreView : EpoxyModel<View>()