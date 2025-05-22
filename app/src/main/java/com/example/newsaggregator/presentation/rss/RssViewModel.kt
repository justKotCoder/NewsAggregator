package com.example.newsaggregator.presentation.rss

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RssViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RssUiState>(RssUiState.Loading)
    val uiState: StateFlow<RssUiState> = _uiState

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            _uiState.value = RssUiState.Loading
            try {
                val news = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                    getNewsUseCase()
                }
                _uiState.value = RssUiState.Success(news)
            } catch (e: Exception) {
                _uiState.value = RssUiState.Error("Ошибка загрузки новостей: ${e.message}")
            }
        }
    }


    fun refreshNews() {
        loadNews()
    }
}
