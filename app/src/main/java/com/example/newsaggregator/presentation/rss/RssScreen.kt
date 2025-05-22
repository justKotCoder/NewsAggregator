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
                title = { Text(item.title) },
                text = {
                    Column {
                        Text(text = item.description)
                        Text(text = "Автор: ${item.author ?: "Неизвестен"}")
                        Text(text = "Категории: ${item.categories.joinToString()}")
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
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            item.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.pubDate, style = MaterialTheme.typography.body2)
                item.author?.let {
                    Text(text = "Автор: $it", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}
