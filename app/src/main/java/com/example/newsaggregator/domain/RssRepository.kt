package com.example.newsaggregator.domain

interface RssRepository {
    suspend fun getNews(): List<Item>
}
