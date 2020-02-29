package com.dsm.base.ui

import androidx.annotation.IdRes
import com.dsm.base.R

enum class PageConfiguration(
    val id: Int,
    val isTopLevel: Boolean = false,
    val hideToolbar: Boolean = false
) {
    SPLASH(
        R.id.splashFragment,
        hideToolbar = true
    ),
    LOGIN(
        R.id.loginFragment,
        hideToolbar = true
    ),
    REGISTER1(R.id.register1Fragment),
    REGISTER2(R.id.register2Fragment),
    REGISTER3(R.id.register3Fragment),
    REGISTER4(R.id.register4Fragment),
    CATEGORY_SELECT(R.id.categorySelectFragment),
    ADDRESS_SEARCH(R.id.addressSearchFragment),
    OTHER(-1);

    companion object {
        fun getConfiguration(@IdRes id: Int): PageConfiguration {
            return values().firstOrNull { it.id == id } ?: OTHER
        }
    }
}