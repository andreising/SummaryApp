package com.andreising.summaryapp.presentation.navigation

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.requireParcelable(key: String): T =
    (if (SDK_INT >= 33) getParcelable(key, T::class.java)
    else @Suppress("DEPRECATION") getParcelable(key) as? T)
        ?: error("Parcelable argument \"$key\" is null")