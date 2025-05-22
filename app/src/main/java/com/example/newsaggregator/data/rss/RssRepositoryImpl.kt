package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.rss.RssDataSource
import com.example.newsaggregator.domain.Item
import com.example.newsaggregator.domain.RssRepository
import javax.inject.Inject

class RssRepositoryImpl @Inject constructor(
    private val dataSource: RssDataSource
) : RssRepository {
    override suspend fun getNews(): List<Item> {
        return dataSource.getRssItems("https://www.theguardian.com/world/rss")
    }
}
