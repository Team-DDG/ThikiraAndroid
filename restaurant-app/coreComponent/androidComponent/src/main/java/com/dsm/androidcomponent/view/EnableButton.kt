package com.dsm.androidcomponent.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.dsm.androidcomponent.R

class EnableButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    private var enable: Boolean
    private val enableBackground: Int
    private val disableBackground: Int
    private val enableTextColor: Int
    private val disableTextColor: Int

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EnableButton)

        enable = typedArray.getBoolean(R.styleable.EnableButton_enable, true)
        isClickable = enable

        enableBackground =
            typedArray.getResourceId(R.styleable.EnableButton_enableBackground, R.drawable.bg_button_enable)
        disableBackground =
            typedArray.getResourceId(R.styleable.EnableButton_disableBackground, R.drawable.bg_button_disable)

        enableTextColor =
            typedArray.getResourceId(R.styleable.EnableButton_enableTextColor, R.color.colorPrimary)
        disableTextColor =
            typedArray.getResourceId(R.styleable.EnableButton_disableTextColor, R.color.colorGrey)

        setupViewByEnable()
    }


    fun setEnable(newEnable: Boolean) {
        enable = newEnable
        setupViewByEnable()
    }

    private fun setupViewByEnable() {
        if (enable) {
            isClickable = enable
            setBackgroundResource(enableBackground)
            setTextColor(ContextCompat.getColor(context, enableTextColor))
        } else {
            isClickable = enable
            setBackgroundResource(disableBackground)
            setTextColor(ContextCompat.getColor(context, disableTextColor))
        }
    }
}