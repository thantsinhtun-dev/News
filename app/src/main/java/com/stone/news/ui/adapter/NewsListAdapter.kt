package com.stone.news.ui.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stone.movieapp.delegates.NewsItemDelegate
import com.stone.news.R
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.databinding.ItemNewsBinding
import com.stone.news.utils.PrettyTimeAgo

class NewsListAdapter(val newsItemDelegate: NewsItemDelegate) : ListAdapter<NewsVO, NewsListAdapter.NewsListViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }



    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bind(getItem(position))
        if(position == currentList.count() -1){
            holder.binding.newsDivider.visibility = View.GONE
        }

    }

    inner class NewsListViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vo: NewsVO) {

            binding.newsTitle.text = vo.title
            Glide.with(itemView.context)

                .load(vo.urlToImage)
                .placeholder(R.drawable.ic_place_holder)
                .into(binding.newsImg)

            try {

                binding.newsPublishDate.text = PrettyTimeAgo.getTimeAgo(itemView.context, vo.publishedAt, "yyyy-MM-dd'T'HH:mm:ss'Z'")
            }catch (e:Exception){

            }
            if (vo.bookmark ){
                binding.imgBookmark.setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.SRC_IN)
            }else{
                binding.imgBookmark.setColorFilter(Color.parseColor("#606060"), PorterDuff.Mode.SRC_IN)
            }

            binding.imgBookmark.setOnClickListener {
                vo.bookmark = !vo.bookmark
                if (vo.bookmark ){
                    binding.imgBookmark.setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.SRC_IN)
                }else{
                    binding.imgBookmark.setColorFilter(Color.parseColor("#606060"), PorterDuff.Mode.SRC_IN)
                }
                newsItemDelegate.onClickBookMark(vo)
            }

            itemView.setOnClickListener {
                vo.bookMarkTime = System.currentTimeMillis()
                newsItemDelegate.onClickNews(vo)
            }
        }
    }


}
private val DIFF_CALLBACK
    get() = object : DiffUtil.ItemCallback<NewsVO>() {
        override fun areItemsTheSame(oldItem: NewsVO, newItem: NewsVO): Boolean {
            return (oldItem.url == newItem.url) and
                    (oldItem.title == newItem.title) and
                    (oldItem.author == newItem.author) and
                    (oldItem.urlToImage == newItem.urlToImage) and
                    (oldItem.publishedAt == newItem.publishedAt) and
                    (oldItem.description == newItem.description) and
            (oldItem.saveFromDetail == newItem.saveFromDetail)
        }

        override fun areContentsTheSame(oldItem: NewsVO, newItem: NewsVO): Boolean {
            return (oldItem.url == newItem.url) and
                    (oldItem.title == newItem.title) and
                    (oldItem.author == newItem.author) and
                    (oldItem.urlToImage == newItem.urlToImage) and
                    (oldItem.publishedAt == newItem.publishedAt) and
                    (oldItem.saveFromDetail == newItem.saveFromDetail)
        }
    }