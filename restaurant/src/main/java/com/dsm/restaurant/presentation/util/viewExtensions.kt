package com.dsm.restaurant.presentation.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun Fragment.setupSnackbar(
    snackbarEvent: LiveData<Int>,
    timeLength: Int = Snackbar.LENGTH_SHORT
) {
    snackbarEvent.observe(viewLifecycleOwner, Observer { stringResId ->
        view?.let { view -> Snackbar.make(view, stringResId, timeLength).show() }
    })
}

fun Fragment.setupToast(
    toastEvent: LiveData<Int>,
    timeLength: Int = Toast.LENGTH_SHORT
) {
    toastEvent.observe(viewLifecycleOwner, Observer { stringResId ->
        Toast.makeText(activity, stringResId, timeLength).show()
    })
}

fun Fragment.hideKeyborad() {
    (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}