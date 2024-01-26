package com.plcoding.koinguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.koinguide.ui.NewsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    var _newsFlow = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsFlow = _newsFlow.asStateFlow()


    fun getNews() = viewModelScope.launch {
        repo.getBreakingNews("us", 1).let { res ->
            if (res.isSuccess) {
                _newsFlow.update {
                    println("articles are ")
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
}