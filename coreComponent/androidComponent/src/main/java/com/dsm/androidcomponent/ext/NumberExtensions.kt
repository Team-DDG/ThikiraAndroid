package com.dsm.androidcomponent.ext

import java.text.NumberFormat
import java.util.*

fun Number.numberAutoComma(): String {
    return NumberFormat.getNumberInstance(Locale.US).format(this)
}