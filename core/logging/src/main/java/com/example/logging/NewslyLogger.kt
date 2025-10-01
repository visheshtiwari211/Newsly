package com.example.logging

import timber.log.Timber

class NewslyLogger: LoggingInterface {
    override fun d(tag: String, msg: String) {
        Timber.d(tag, msg)
    }

    override fun e(tag: String, msg: String, throwable: Throwable?) {
        Timber.e(tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Timber.i(tag, msg)
    }

    override fun w(tag: String, msg: String, throwable: Throwable?) {
        Timber.w(tag, msg)
    }
}
