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

    ORDER(R.id.orderFragment, hideToolbar = true),
    MENU(R.id.menuFragment, hideToolbar = true),
    COUPON(R.id.couponFragment, isTopLevel = true),
    RESTAURANT(R.id.restaurantFragment, hideToolbar = true),

    MENU_CATEGORY(R.id.menuCategoryFragment, hideToolbar = true),
    MENU_REGISTRATION1(R.id.menuRegistration1Fragment),

    COUPON_ISSUE(R.id.couponIssueDialog, isTopLevel = true),

    OTHER(-1);

    companion object {
        fun getConfiguration(@IdRes id: Int): PageConfiguration {
            return values().firstOrNull { it.id == id } ?: OTHER
        }
    }
}