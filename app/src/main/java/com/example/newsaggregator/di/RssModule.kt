package com.example.newsaggregator.di

import com.example.newsaggregator.data.repository.RssRepositoryImpl
import com.example.newsaggregator.data.rss.RssDataSource
import com.example.newsaggregator.domain.RssRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RssModule {

    @Provides
    @Singleton
    fun provideRssDataSource(): RssDataSource {
        return RssDataSource()
    }

    @Provides
    @Singleton
    fun provideRssRepository(
        rssDataSource: RssDataSource
    ): RssRepository {
        return RssRepositoryImpl(rssDataSource)
    }
}
