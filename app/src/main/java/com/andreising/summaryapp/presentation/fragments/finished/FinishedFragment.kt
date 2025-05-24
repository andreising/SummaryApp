package com.andreising.summaryapp.presentation.fragments.finished

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentFinishedBinding
import dev.androidbroadcast.vbpd.viewBinding

class FinishedFragment : Fragment(R.layout.fragment_finished) {

    val binding: FragmentFinishedBinding by viewBinding(FragmentFinishedBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}