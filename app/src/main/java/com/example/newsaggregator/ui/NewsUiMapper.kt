package com.example.newsaggregator.ui

import com.example.newsaggregator.domain.Item


fun Item.toUiModel(): NewsUiModel {
    return NewsUiModel(
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        pubDate = this.pubDate,
        author = this.author,
        link = this.link,
        categories = this.categories
    )
}
