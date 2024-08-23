package com.marwa.androidproject.presentation.home.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marwa.androidproject.databinding.TagItemBinding

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {
    private val genreIds = arrayListOf<Int>()

    inner class TagsViewHolder(private val binding: TagItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.tvTag.text = "${genreIds[position]}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder =
        TagsViewHolder(
            TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = genreIds.size

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setData(genreIds: List<Int>) {
        this.genreIds.clear()
        this.genreIds.addAll(genreIds)
        notifyDataSetChanged()

    }
}