package com.example.newsaggregator.presentation.rss

import com.example.newsaggregator.domain.Item

sealed class RssUiState {
    object Loading : RssUiState()
    data class Success(val news: List<Item>) : RssUiState()
    data class Error(val message: String) : RssUiState()
}

