package com.example.newsapp.Flow_operators

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

// This is sequential, so kind of blocking like you said
fun flowFrom(elem: String) = flowOf(1, 2, 3)
    .onEach { delay(1000) }
    .map { "${it}_${elem} " }

suspend fun main() {
    flowOf("A", "B", "C")
        .flatMapConcat { flowFrom(it) }
        .collect { println(it) }
}