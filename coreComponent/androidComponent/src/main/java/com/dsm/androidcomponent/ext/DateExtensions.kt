package com.dsm.androidcomponent.ext

import java.text.SimpleDateFormat
import java.util.*

fun formatToIso(date: Date): String =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA).format(date)

fun parseFromIso(string: String): Date =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA).parse(string) ?: Date()