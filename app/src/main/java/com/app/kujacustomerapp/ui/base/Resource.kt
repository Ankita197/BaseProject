package com.app.kujacustomerapp.ui.base

class Resource<T>(val status: Status) {
    var item: T? = null
    private var throwable: Throwable? = null

    constructor(status: Status, item: T?) : this(status) {
        this.item = item
    }

    constructor(status: Status, throwable: Throwable) : this(
        status
    ) {
        this.throwable = throwable
    }

}