package com.example.logging

import timber.log.Timber

class NewslyLogger : LoggingInterface {
    override fun d(tag: String, msg: String) {
        Timber.tag(tag).d(msg)
    }

    override fun e(tag: String, msg: String, throwable: Throwable?) {
        Timber.tag(tag).e(throwable, msg)
    }

    override fun i(tag: String, msg: String) {
        Timber.tag(tag).i(msg)
    }

    override fun w(tag: String, msg: String, throwable: Throwable?) {
        Timber.tag(tag).w(throwable, msg)
    }
}
