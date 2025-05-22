package com.example.newsaggregator.ui


data class NewsUiModel(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val pubDate: String,
    val author: String?,
    val link: String,
    val categories: List<String>
)
