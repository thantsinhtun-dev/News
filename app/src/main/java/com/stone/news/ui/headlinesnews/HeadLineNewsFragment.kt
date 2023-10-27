package com.stone.news.ui.headlinesnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.stone.movieapp.delegates.NewsItemDelegate
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.databinding.FragmentHeadLineNewsBinding
import com.stone.news.ui.adapter.NewsListAdapter
import com.stone.news.ui.base.BaseFragment


class HeadLineNewsFragment : BaseFragment(),NewsItemDelegate {


    private lateinit  var binding : FragmentHeadLineNewsBinding

    private lateinit var newsListAdapter: NewsListAdapter

    private lateinit var viewModel : HeadlinesNewsVM
//    by viewModels<HeadlinesNewsVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadLineNewsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[HeadlinesNewsVM::class.java]


        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()


        observedData()

    }

    override fun onResume() {
        super.onResume()
        Log.i("Test Tabselected onResume","onResume ${viewModel.selectedTabPosition}")
        setUpListener()

    }


    private fun observedData(){
        viewModel.categoryList.observe(viewLifecycleOwner) { data ->

            data?.forEach {vo->
                binding.tabLayoutGenre.newTab().apply {
                    text = vo.name
                    binding.tabLayoutGenre.addTab(this)
                }

            }
           val tab = binding.tabLayoutGenre.getTabAt(viewModel.selectedTabPosition)

            tab?.select()
        }
        viewModel.newsList.observe(viewLifecycleOwner) { data ->

            newsListAdapter.submitList(data)
        }
    }



    private fun setUpListener(){


        binding.tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {


                    viewModel.loadNewsByCategory(tab?.position ?: 0)
                    viewModel.selectedTabPosition = tab?.position ?: 0

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setUpRecyclerView() {
        newsListAdapter = NewsListAdapter(this)
        binding.rvNewsList.adapter = newsListAdapter
    }

    override fun onClickNews(vo: NewsVO) {
//        viewModel.updateNews(vo)
    }

    override fun onClickBookMark(vo: NewsVO) {
        viewModel.updateNews(vo)
    }


}