package com.example.newsapp.Comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*

Let’s consider a real-world scenario. Suppose we are working with an API that provides us a flow of user IDs,
 and for each user ID, we want to load detailed user data from another API. Here, flatMapMerge can be quite useful:


 */

data class CommentsState(
    val posts: List<Post>,
    val comments: List<Comment>
)

class CommentsViewModel(
    private val api: CommentsApi
) : ViewModel() {

    var state = MutableStateFlow(CommentsState(emptyList(), emptyList()))

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPosts() = viewModelScope.launch {
        /*
        Why we use this only, no need to switch onto another flow, just map is good here
        - map already defined in suspend, no need to block the thread here
        - using the following
         */
//        return

        val posts = api.getPosts()
//        println("posts are $posts")
        state.update { it.copy(posts = posts) }
        var finalLists = mutableListOf<Post>()
        posts.asFlow().flatMapMerge { post ->
            return@flatMapMerge getComments(post).map {
                post.comments = it
                post
            }
        }.collect() { post ->
            updateList(post)
        }
    }

    private fun getComments(post: Post) = flow {
        emit(api.getComments(post.id))
    }

    fun updateList(post: Post) {
        val posts = state.value.posts.toMutableList()
        val idx = posts.indexOfFirst { it.id == post.id }
        posts[idx] = post
        state.update {
            it.copy(posts = posts.toList())
        }
    }
}










