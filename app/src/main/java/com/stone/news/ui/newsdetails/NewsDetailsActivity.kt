package com.stone.news.ui.newsdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stone.news.R
import com.stone.news.databinding.ActivityMainBinding
import com.stone.news.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}