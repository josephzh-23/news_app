package com.example.newsapp.News

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.koinguide.ui.NewsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class ViewState(
    val showLoading: Boolean = false,
    val title: String = "Default Title",
    val doneButtonEnabled: Boolean = true
)

class MainViewModel(
    private val repo: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    var _newsFlow = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsFlow = _newsFlow.asStateFlow()

    private val sortFlow = MutableStateFlow(SortType.NONE)

    // This will then display on the answer before
    val searchResults =
        combine(
            searchText
                .debounce(1000L)
                .onEach
                { _isSearching.update { true } }, _newsFlow
        )
        { text, news ->
            if (text.isBlank()) {
                news
            } else {
                delay(1000L)
                if (news is NewsState.NewsResponse) {
                    val ans = news.articles.filter {
                        it.content?.contains(text) == true
                    }

                    println("the size is ${ans.size}")
                    applySort(NewsState.NewsResponse(ans))
                } else {
                    news
                }

            }
        }.onEach { _isSearching.update { false } }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                _newsFlow.value
            )


    fun getNews() = viewModelScope.launch {
        repo.getBreakingNews("us", 1).let { res ->
            if (res.isSuccess) {
                _newsFlow.update {
                    println("articles are ${res.getOrNull()?.articles}")
                    res.getOrNull()?.articles?.takeIf { it.isNotEmpty() }?.let {
                        NewsState.NewsResponse(it)
                    } ?: NewsState.NewsResponse(emptyList())
                }
            } else {
                _newsFlow.update {
                    NewsState.Error
                }
            }
        }
    }

    fun applySort(state: NewsState.NewsResponse): NewsState {
        return when (sortFlow.value) {

            SortType.DATE -> {
                val updatedList = state.articles.sortedBy { it.publishedAt }
                NewsState.NewsResponse(updatedList)
            }

            SortType.NAME -> {
                val updatedList = state.articles.sortedBy { it.title }
                NewsState.NewsResponse(updatedList)
            }

            SortType.NONE -> state

        }
    }


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}