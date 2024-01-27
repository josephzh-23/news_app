package com.example.newsapp.Flow_operators

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

// Concurrent
// Calls sequentially but merge concurrently

fun flowFrom2(elem: String) = flowOf(1, 2, 3)
    .onEach { delay(1000) }
    .map { "${it}_${elem} " }

suspend fun main() {
    flowOf("A", "B", "C")
        .flatMapMerge { flowFrom2(it) }
        .collect { println(it) }
}