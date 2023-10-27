package com.stone.news.ui.newsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stone.news.R
import com.stone.news.data.local.database.entity.NewsVO
import com.stone.news.databinding.ActivityNewsDetailsBinding
import com.stone.news.ui.base.BaseActivity
import com.stone.news.utils.getSerializable

class NewsDetailsActivity : BaseActivity(){
    private lateinit var binding: ActivityNewsDetailsBinding

    private val viewModel: NewsDetailVM by viewModels()

    lateinit var newsVO : NewsVO
    companion object {
        private const val EXTRA_VO = "news_vo"

        fun getIntent(context: Context,vo:NewsVO): Intent {
            val intent =  Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(EXTRA_VO, vo)
            return  intent

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsVO = intent.getSerializable(EXTRA_VO, NewsVO::class.java)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = newsVO.title


        loadWebView(newsVO.url)


        initUI()

        setUpObserver()
        setUpListener()


    }

    private fun setUpObserver(){
        viewModel.isAddedFavorite.observe(this){
            if(it){
                binding.floatingButton.setImageResource(R.drawable.ic_favorite_24)

            }else {
                binding.floatingButton.setImageResource(R.drawable.ic_favorite_border_24)

            }
        }
    }
    private fun initUI(){

        if(newsVO.bookmark){
            binding.floatingButton.setImageResource(R.drawable.ic_favorite_24)

        }else {
            binding.floatingButton.setImageResource(R.drawable.ic_favorite_border_24)

        }
    }

    private fun setUpListener(){

        binding.floatingButton.setOnClickListener {
            viewModel.addFavoriteArticle(newsVO)
        }

    }
    private  fun loadWebView(url:String){
            try {
                binding.webView.apply {
                    webViewClient = object : WebViewClient(){
                        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                            super.onReceivedError(view, request, error)
                            binding.webView.visibility = View.GONE
                            binding.layoutNoInternet.visibility = View.VISIBLE
                        }
                    }
                    loadUrl(url)
                }
            }
            catch (e:Exception){
                e.printStackTrace()
            }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}