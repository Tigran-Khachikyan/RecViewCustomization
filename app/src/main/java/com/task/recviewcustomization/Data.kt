package com.task.recviewcustomization

data class Data(
    val date: String,
    val callData: List<Double>,
    val putData: List<Double>,
    val strikeList: List<Double>,
    val headerData: List<String>,
    var scrX : Int? = null
)
