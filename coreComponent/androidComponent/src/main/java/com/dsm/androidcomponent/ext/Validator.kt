package com.dsm.androidcomponent.ext

import java.util.regex.Pattern

fun isValidPassword(password: String) =
    Pattern.compile("^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*+=-]).{6,}$").matcher(password).find()

fun isValidEmail(email: String?) =
    if (email == null) false
    else Pattern.compile("^[0-9a-zA-Z]*[-_.]?[0-9a-zA-Z]*@[0-9a-zA-Z]*[-_.]?[0-9a-zA-Z]*.[a-zA-Z]{2,3}$").matcher(email).find()
