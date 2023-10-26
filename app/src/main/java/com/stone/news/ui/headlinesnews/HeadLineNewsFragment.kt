package com.stone.news.ui.headlinesnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stone.news.R
import com.stone.news.databinding.FragmentHeadLineNewsBinding


class HeadLineNewsFragment : Fragment() {


    private lateinit  var binding : FragmentHeadLineNewsBinding

    private lateinit var newsListAdapter: NewsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadLineNewsBinding.inflate(inflater, container, false)

        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabLayout()
        setUpRecyclerView()

    }



    private fun setUpTabLayout() {
        repeat(20) {
            binding.tabLayoutGenre.newTab().apply {
                text = "it.name"
                binding.tabLayoutGenre.addTab(this)
            }
        }
    }
    private fun setUpRecyclerView() {
        newsListAdapter = NewsListAdapter()
        binding.rvNewsList.adapter = newsListAdapter
    }


}