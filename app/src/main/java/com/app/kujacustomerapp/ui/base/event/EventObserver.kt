package com.app.kujacustomerapp.ui.base.event

import androidx.lifecycle.Observer

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: OnEventUnhandledContent) :
    Observer<T> {
    fun onChanged(event: Event<*>?) {
        if (event != null) {
            val `object` = event.contentIfNotHandled
            if (`object` != null) {
                onEventUnhandledContent.onChanged(`object`)
            }
        }
    }

    override fun onChanged(`object`: T) {
        this.onChanged(`object` as Event<*>)
    }

}