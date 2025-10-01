package com.example.logging

interface LoggingInterface {
    fun d(tag: String = getDefaultTag(), msg: String)
    fun e(tag: String = getDefaultTag(), msg: String, throwable: Throwable? = null)
    fun i(tag: String = getDefaultTag(), msg: String)
    fun w(tag: String = getDefaultTag(), msg: String, throwable: Throwable? = null)
}

fun getDefaultTag() : String {
    val throwable = Throwable()
    return throwable.stackTrace.getOrNull(2)?.fileName ?: "AppLogger"
}