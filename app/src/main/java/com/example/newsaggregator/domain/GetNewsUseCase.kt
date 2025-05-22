package com.example.newsaggregator.domain


import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: RssRepository
) {
    suspend operator fun invoke(): List<Item> {
        return repository.getNews()
    }
}
