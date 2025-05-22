package com.example.newsaggregator.data.rss


import com.example.newsaggregator.domain.Item
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class RssDataSource @Inject constructor() {

    private val client = OkHttpClient()

    fun getRssItems(feedUrl: String): List<Item> {
        val request = Request.Builder().url(feedUrl).build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw RuntimeException("Ошибка запроса: ${response.code}")

            val body = response.body ?: throw RuntimeException("Пустой ответ")
            val input = SyndFeedInput()
            val feed = input.build(XmlReader(body.byteStream()))

            return feed.entries.map { entry ->
                Item(
                    title = entry.title,
                    description = entry.description?.value ?: "",
                    imageUrl = null,
                    pubDate = entry.publishedDate?.toString() ?: "",
                    author = null,
                    link = entry.link,
                    categories = emptyList()
                )
            }
        }
    }
}
