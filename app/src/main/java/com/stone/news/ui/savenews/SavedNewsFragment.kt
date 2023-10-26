package com.stone.news.ui.savenews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stone.news.R
import com.stone.news.databinding.FragmentHeadLineNewsBinding
import com.stone.news.databinding.FragmentSavedNewsBinding
import com.stone.news.ui.headlinesnews.NewsListAdapter


class SavedNewsFragment : Fragment() {

    private lateinit  var binding : FragmentSavedNewsBinding
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        newsListAdapter = NewsListAdapter()
        binding.rvNewsList.adapter = newsListAdapter
    }

}