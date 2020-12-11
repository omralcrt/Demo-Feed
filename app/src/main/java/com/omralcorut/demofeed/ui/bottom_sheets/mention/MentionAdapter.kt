package com.omralcorut.demofeed.ui.bottom_sheets.mention

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.omralcorut.demofeed.databinding.HolderFeedMentionBinding
import com.omralcorut.demofeed.models.Mention

class MentionAdapter : RecyclerView.Adapter<MentionAdapter.ViewHolder>() {

    private val mentions: MutableList<Mention> = mutableListOf()

    fun addAll(newMentions: Array<Mention>) {
        mentions.clear()
        mentions.addAll(newMentions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HolderFeedMentionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mention = mentions[position]
        holder.binding.imageViewUser.load(mention.profileImage)
        holder.binding.textViewUserName.text = mention.fullname
        holder.binding.textViewNickName.text = mention.userName
        holder.binding.buttonFollow.visibility = if (mention.isFollowing) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int = mentions.size

    class ViewHolder(val binding: HolderFeedMentionBinding) : RecyclerView.ViewHolder(binding.root)
}