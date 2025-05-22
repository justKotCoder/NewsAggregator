package com.example.newsaggregator.data.remote.mappers

import com.example.newsaggregator.data.rss.dto.ItemDto
import com.example.newsaggregator.domain.Item


fun ItemDto.toDomain(): Item {
    return Item(
        title = this.title,
        description = this.description,
        imageUrl = null,
        pubDate = this.pubDate,
        author = null,
        link = this.link,
        categories = emptyList(),
    )
}
