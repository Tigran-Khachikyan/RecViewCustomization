package com.task.recviewcustomization

import android.util.Log

data class Data(
    val date: String,
    val callData: List<Double>,
    val putData: List<Double>,
    val strikeList: List<Double>,
    val headerData: List<String>,
    var scrX: Int? = null
)

fun convert(list: List<Double>, columnCount: Int): List<Double> {
    val result = ArrayList<Double>(list.size)
    for(i in 0 until columnCount)
    list.forEachIndexed { index, d ->
        if (index % columnCount == i){
            result.add(d)
        }
    }
    return result
}
