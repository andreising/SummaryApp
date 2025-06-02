package com.andreising.summaryapp.presentation.fragments.utils

import android.widget.TextView

fun setTextWithFormat(view: TextView, resId: Int, vararg values: Any) {
    view.text = view.context.getString(resId, *values)
}

fun setText(view: TextView, value: Any) {
    view.text = value.toString()
}