package com.andreising.summaryapp.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.andreising.summaryapp.R

fun FragmentActivity.setNewFragment(
    fragment: Fragment,
    destinationName: String? = null
) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container, fragment)
        .addToBackStack(destinationName)
        .commit()
}

fun FragmentActivity.popBackStack(name: String?, inclusive: Boolean) {
    supportFragmentManager.popBackStack(
        name,
        if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0
    )
}