package com.carly.core.ext

import com.carly.R
import com.carly.core.data.CarAlreadyExistsException

/**
 * Get error resource id for throwable
 */
fun Throwable.getErrorResourceId(): Int {
    return if (this is CarAlreadyExistsException) {
        R.string.car_already_exists
    } else {
        R.string.ops_something_went_wong
    }
}