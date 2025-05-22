package com.example.newsaggregator.domain

data class Item(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val pubDate: String,
    val author: String?,
    val link: String,
    val categories: List<String>
)
