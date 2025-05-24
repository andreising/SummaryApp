package com.andreising.summaryapp.presentation.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentWelcomeBinding
import dev.androidbroadcast.vbpd.viewBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    val binding: FragmentWelcomeBinding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

}