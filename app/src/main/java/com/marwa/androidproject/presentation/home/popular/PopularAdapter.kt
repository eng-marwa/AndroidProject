package com.marwa.androidproject.presentation.home.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.marwa.androidproject.data.datasource.remote.api.imageUrl
import com.marwa.androidproject.data.model.Result
import com.marwa.androidproject.databinding.PopularItemBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    private lateinit var context: Context
    private val results = arrayListOf<Result>()

    inner class PopularViewHolder(private val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.tvMovieTitle.text = results[position].originalTitle
            binding.tvRating.text = results[position].voteAverage
            binding.tvDate.text = results[position].releaseDate
            Glide.with(context).asBitmap().load("$imageUrl${results[position].posterPath}")
                .into(binding.ivPopularMovie)

            setupTagsRV(binding.rvTags,results[position].genreIds)
        }
    }

    private fun setupTagsRV(rvTags: RecyclerView, genreIds: List<Int>, ) {
        val tagsAdapter = TagsAdapter()
        rvTags.adapter = tagsAdapter
        rvTags.layoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        tagsAdapter.setData(genreIds)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        context = parent.context
        return PopularViewHolder(
            PopularItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setData(results: List<Result>) {
        this.results.clear()
        this.results.addAll(results)
        notifyDataSetChanged()
    }
}