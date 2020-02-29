package com.dsm.androidcomponent.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun Fragment.setupNavigateEvent(
    navigateEvent: LiveData<Int>
) {
    navigateEvent.observe(viewLifecycleOwner, Observer { navigateId ->
        findNavController().navigate(navigateId)
    })
}

fun Fragment.setupSnackbarEvent(
    snackbarEvent: LiveData<Int>,
    timeLength: Int = Snackbar.LENGTH_SHORT
) {
    snackbarEvent.observe(viewLifecycleOwner, Observer { stringResId ->
        view?.let { view -> Snackbar.make(view, stringResId, timeLength).show() }
    })
}

fun Fragment.setupToastEvent(
    toastEvent: LiveData<Int>,
    timeLength: Int = Toast.LENGTH_SHORT
) {
    toastEvent.observe(viewLifecycleOwner, Observer { stringResId ->
        Toast.makeText(activity, stringResId, timeLength).show()
    })
}

fun Fragment.hideKeyboard() {
    (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}