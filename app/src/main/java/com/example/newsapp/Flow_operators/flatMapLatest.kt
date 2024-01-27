package com.example.newsapp.Flow_operators

/*

The last function is flatMapLatest. It forgets about the previous flow once a new one appears. With every new value, the previous flow processing is forgotten.
So, if there is no delay between "A", "B" and "C", then you will only see "1_C", "2_C", and "3_C".
 */

