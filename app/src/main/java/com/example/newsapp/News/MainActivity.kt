package com.example.newsapp.News

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.Comments.CommentScreen
import com.example.newsapp.R
import com.example.random.ui.theme.KoinGuideTheme
import com.plcoding.koinguide.ui.NewsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
//    , AndroidScopeComponent

//    private val viewModel by viewModel<MainViewModel>()
//    override val scope: Scope by activityScope()
//    private val hello by inject<String>(named("bye"))

    val sortFlow = MutableStateFlow(SortType.NONE)
    // Using a flow flatMapLatest will get you the latest value and cancel previous block as said


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinGuideTheme {

                CommentScreen()
                val viewModel = getViewModel<MainViewModel>()
//                LaunchedEffect(Unit) {
//                    viewModel.getNews()
//                }
//                val searchText by viewModel.searchText.collectAsState()
//                val isSearching by viewModel.isSearching.collectAsState()
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp)
//                ) {
//                    TextField(
//                        value = searchText,
//                        onValueChange = viewModel::onSearchTextChange,
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = { Text(text = "Search") }
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//                    if (isSearching) {
//                        Box(modifier = Modifier.fillMaxSize()) {
//                            CircularProgressIndicator(
//                                modifier = Modifier.align(Alignment.Center)
//                            )
//                        }
//                    } else {
//
//                        viewModel.searchResults.collectAsStateWithLifecycle().let {
//
//                            when (it.value) {
//                                is NewsState.Loading -> println("loading")
//                                is NewsState.Error -> println("error")
//                                is NewsState.NewsResponse -> {
//
//                                    println("the value is ${(it.value as NewsState.NewsResponse).articles}")
//
//                                    LazyColumn() {
//                                        items((it.value as NewsState.NewsResponse).articles) {
//
//                                            Row() {
//                                                AsyncImage(
//                                                    model = ImageRequest.Builder(
//                                                        LocalContext.current
//                                                    )
//                                                        .data(it.urlToImage)
//                                                        .crossfade(true)
//                                                        .build(),
//                                                    placeholder = painterResource(R.drawable.cool),
//                                                    contentDescription = stringResource(R.string.app_name),
//                                                    contentScale = ContentScale.Crop,
//                                                    modifier = Modifier.size(50.dp),
//                                                )
//                                                Column(modifier = Modifier.padding(start= 20.dp)) {
//                                                    if (!it.title.isNullOrEmpty()) {
//                                                        Text(it.title, maxLines = 2)
//                                                    }
//                                                    if (!it.content.isNullOrEmpty()) {
//                                                        Text(it.content, modifier = Modifier.padding(top = 20.dp))
//                                                    }
//                                                }
//                                            }
//
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}


enum class SortType {
    NAME, DATE, NONE
}
