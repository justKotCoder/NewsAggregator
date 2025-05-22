package com.example.newsaggregator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsaggregator.presentation.rss.RssScreen
import com.example.newsaggregator.presentation.rss.RssViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: RssViewModel = hiltViewModel()
            RssScreen(viewModel = viewModel)
        }
    }
}
