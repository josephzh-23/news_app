package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.random.ui.theme.KoinGuideTheme
import com.plcoding.koinguide.MainViewModel
import com.plcoding.koinguide.ui.NewsState
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
//    , AndroidScopeComponent

//    private val viewModel by viewModel<MainViewModel>()
//    override val scope: Scope by activityScope()
//    private val hello by inject<String>(named("bye"))

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinGuideTheme {
                val viewModel = getViewModel<MainViewModel>()
                LaunchedEffect(Unit) {
                    viewModel.getNews()
                }

                Column() {

                    viewModel.newsFlow.collectAsStateWithLifecycle().let {

                        when (it.value) {

                            is NewsState.Loading -> println("loading")
                            is NewsState.Error -> println("error")
                            is NewsState.NewsResponse -> {
                                LazyColumn() {
                                    items((it.value as NewsState.NewsResponse).articles) {

                                        Row() {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(it.urlToImage)
                                                    .crossfade(true)
                                                    .build(),
                                                placeholder = painterResource(R.drawable.cool),
                                                contentDescription = stringResource(R.string.app_name),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.size(50.dp),
                                            )
                                            Column() {
                                                Text(it.title)
                                                Text(it.content)
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}