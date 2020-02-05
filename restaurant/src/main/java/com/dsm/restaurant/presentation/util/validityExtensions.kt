package com.dsm.restaurant.presentation.util

import java.util.regex.Pattern

fun isValidPassword(password: String) =
    Pattern.compile("^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*+=-]).{6,}$").matcher(password).find()

fun isValidEmail(email: String) =
    Pattern.compile("^[0-9a-zA-Z]*[-_.]?[0-9a-zA-Z]*@[0-9a-zA-Z]*[-_.]?[0-9a-zA-Z]*.[a-zA-Z]{2,3}$").matcher(email).find()