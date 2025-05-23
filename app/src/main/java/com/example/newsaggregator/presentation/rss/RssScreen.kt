@file:OptIn(ExperimentalMaterialApi::class)
package com.example.newsaggregator.presentation.rss

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newsaggregator.domain.Item
import androidx.core.text.HtmlCompat

@Composable
fun RssScreen(
    viewModel: RssViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing = uiState is RssUiState.Loading
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.refreshNews() }
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .pullRefresh(pullRefreshState)) {

        when (uiState) {
            is RssUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is RssUiState.Success -> {
                val news = (uiState as RssUiState.Success).news
                NewsList(news)
            }

            is RssUiState.Error -> {
                val message = (uiState as RssUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = message, color = MaterialTheme.colors.error)
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun NewsList(news: List<Item>) {
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(news) { item ->
                NewsCard(item = item) {
                    selectedItem = item
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        selectedItem?.let { item ->
            AlertDialog(
                onDismissRequest = { selectedItem = null },
                confirmButton = {
                    TextButton(onClick = { selectedItem = null }) {
                        Text("Закрыть")
                    }
                },
                title = { Text(text = item.title.cleanHtml()) },
                text = {
                    Column(modifier = Modifier.padding(top = 8.dp)) {
                        item.description?.let {
                            Text(
                                text = it.cleanHtml(),
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(
                            text = "Автор: ${item.author ?: "Неизвестен"}",
                            style = MaterialTheme.typography.body2
                        )
                        if (item.categories.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Категории: ${item.categories.joinToString()}",
                                style = MaterialTheme.typography.body2
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Дата публикации: ${item.pubDate}",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )

                    }
                }
            )
        }
    }
}


@Composable
fun NewsCard(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp) // Минимальная высота карточки
            .clickable { onClick() },
        elevation = 6.dp, // немного больше "всплытие"
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            item.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp) // увеличено
                        .padding(end = 20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.pubDate,
                    style = MaterialTheme.typography.body2
                )
                item.author?.let {
                    Text(
                        text = "Автор: $it",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}


fun String.cleanHtml(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}

