package com.stone.news.ui.savenews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.stone.movieapp.delegates.NewsItemDelegate
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.databinding.FragmentSavedNewsBinding
import com.stone.news.ui.base.BaseFragment
import com.stone.news.ui.adapter.NewsListAdapter
import com.stone.news.ui.newsdetails.NewsDetailsActivity


class SavedNewsFragment : BaseFragment() , NewsItemDelegate {

    private lateinit  var binding : FragmentSavedNewsBinding
    private lateinit var newsListAdapter: NewsListAdapter

    private val viewModel: SaveNewsVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchSavedNews()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Test ","onViewCreated")

        setUpRecyclerView()

        setUpObserver()
    }

    private fun setUpObserver(){
        viewModel.newsList.observe(viewLifecycleOwner){
            newsListAdapter.submitList(it)
        }
    }
    private fun setUpRecyclerView() {
        newsListAdapter = NewsListAdapter(this)
        binding.rvNewsList.adapter = newsListAdapter
    }

    override fun onClickNews(vo: NewsVO) {
        startActivity(NewsDetailsActivity.getIntent(requireContext(),vo))

    }

    override fun onClickBookMark(vo: NewsVO) {
        viewModel.updateNews(vo)
    }

}