package com.dsm.androidcomponent.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.navigation.findNavController
import com.dsm.androidcomponent.R
import kotlinx.android.synthetic.main.layout_default_toolbar.view.*

class DefaultToolbar(context: Context, attrs: AttributeSet) : @JvmOverloads LinearLayout(context, attrs) {

    val view: View

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.layout_default_toolbar, this, false)
        addView(view)
        view.tb_default_toolbar.setOnClickListener { findNavController().popBackStack() }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultToolbar)
        val title = typedArray.getString(R.styleable.DefaultToolbar_title)
        setTitle(title ?: "")

        val isTopLevel = typedArray.getBoolean(R.styleable.DefaultToolbar_isTopLevel, false)
        if (isTopLevel) view.tb_default_toolbar.navigationIcon = null
    }

    fun setTitle(title: String) {
        view.tv_title.text = title
    }

    fun setTitle(@StringRes title: Int) {
        view.tv_title.text = context.getString(title)
    }
}