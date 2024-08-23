package com.marwa.androidproject.presentation.home.now_showing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marwa.androidproject.data.datasource.remote.api.imageUrl
import com.marwa.androidproject.data.model.Result
import com.marwa.androidproject.databinding.NowShowingItemBinding

class NowShowingAdapter : RecyclerView.Adapter<NowShowingAdapter.NowShowingViewHolder>() {
    private val results = arrayListOf<Result>()
    private lateinit var context: Context

    inner class NowShowingViewHolder(private val binding: NowShowingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.tvMovieTitle.text = results[position].originalTitle
            binding.tvRating.text = results[position].voteAverage
            Glide.with(context).asBitmap().load("$imageUrl${results[position].posterPath}")
                .into(binding.ivMoviePic)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        context = parent.context
        return NowShowingViewHolder(
            NowShowingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setData(results: List<Result>) {
        this.results.clear()
        this.results.addAll(results)
        notifyDataSetChanged()
    }
}