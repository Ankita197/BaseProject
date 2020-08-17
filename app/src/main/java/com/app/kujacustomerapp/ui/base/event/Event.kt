package com.app.kujacustomerapp.ui.base.event

class Event<T>(private val content: T) {
    var isHasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    val contentIfNotHandled: T?
        get() {
            val `object`: T?
            if (isHasBeenHandled) {
                `object` = null
            } else {
                isHasBeenHandled = true
                `object` = content
            }
            return `object`
        }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T {
        return content
    }

}