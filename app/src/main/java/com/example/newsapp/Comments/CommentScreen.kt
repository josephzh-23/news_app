package com.example.newsapp.Comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.News.MainViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CommentScreen() {



    val viewModel = getViewModel<CommentsViewModel>()


    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }


    viewModel.state.collectAsStateWithLifecycle().let { state ->

        LazyColumn() {

            items(state.value.posts) { post ->
                Text(text = post.title, color = Color.Blue)
                FlowColumn(Modifier.padding(top = 20.dp)) {
                    if (post.comments == null) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                        }

                    } else {
                        post.comments?.forEach {
                            Text(it.body, color = Color.Black)
                        }
                    }
                }
            }
        }

    }
}