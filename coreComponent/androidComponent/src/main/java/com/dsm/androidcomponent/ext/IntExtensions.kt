package com.dsm.androidcomponent.ext

import java.text.NumberFormat
import java.util.*

fun Int.numberAutoComma() {
    NumberFormat.getNumberInstance(Locale.US).format(this)
}