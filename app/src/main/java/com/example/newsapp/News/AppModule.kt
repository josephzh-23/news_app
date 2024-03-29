package com.example.newsapp.News

import com.example.newsapp.Comments.CommentsApi
import com.example.newsapp.Comments.CommentsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val API_KEY = "80835d6cd1ba4d3ba33448d06967e539"
val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    // For the comment section here
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CommentsApi::class.java)
    }
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
    // Get is used to do this here, this is the same as bind here
    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    // If you have a mapper we can use it here
//    single {
//        CoolMapper()
//    }
    viewModel {
        MainViewModel(get(), get(named("IODispatcher")))
    }
    viewModel {
        CommentsViewModel(get())
    }
}











