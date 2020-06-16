package com.dsm.androidcomponent.ext

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

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

fun LifecycleOwner.setupSnackbarEvent(
    snackbarEvent: LiveData<Int>,
    view: View,
    timeLength: Int = Snackbar.LENGTH_SHORT
) {
    snackbarEvent.observe(this, Observer { stringResId ->
        Snackbar.make(view, stringResId, timeLength)
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

fun AppCompatActivity.setupToastEvent(
    toastEvent: LiveData<Int>,
    timeLength: Int = Toast.LENGTH_SHORT
) {
    toastEvent.observe(this, Observer { stringResId ->
        Toast.makeText(this, stringResId, timeLength).show()
    })
}

fun Fragment.hideKeyboard() {
    (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}

fun AppCompatActivity.hideKeyboard() {
    (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow( window.attributes.token, 0)
}

fun AppCompatActivity.setupNavigateEvent(
    navigateEvent: LiveData<Activity>
) {
    navigateEvent.observe(this, Observer { navigateEvent ->
        startActivity(Intent(this, navigateEvent::class.java))
    })
}

inline fun <reified T: Activity> AppCompatActivity.startActivity(){
    startActivity(Intent(this, T::class.java))
}

fun Spinner.onItemSelectedListener(onItemSelected: (selectedItem: String) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            try {
                val selected = parent?.selectedItem?.toString() ?: ""
                onItemSelected.invoke(selected)
            } catch (e: NoSuchElementException) {
                e.printStackTrace()
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}

@BindingAdapter("priceText")
fun TextView.bindPriceText(price: Int) {
    val formatter = DecimalFormat("#,###")
    this.text = formatter.format(price)
}