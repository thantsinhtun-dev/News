package com.stone.news.ui.headlinesnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stone.news.data.remote.model.NewsVO
import com.stone.news.databinding.ItemNewsBinding

class NewsListAdapter : ListAdapter<NewsVO, NewsListAdapter.NewsListViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
//     holder.bind()
    }

    inner class NewsListViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vo : NewsVO){

        }
    }
}

private val DIFF_CALLBACK
    get() = object : DiffUtil.ItemCallback<NewsVO>() {
        override fun areItemsTheSame(oldItem: NewsVO, newItem: NewsVO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsVO, newItem: NewsVO): Boolean {
            return (oldItem.id == newItem.id) and
                    (oldItem.title == newItem.title)
        }
    }