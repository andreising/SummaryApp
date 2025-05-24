package com.andreising.summaryapp.presentation.fragments.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentGameBinding
import dev.androidbroadcast.vbpd.viewBinding

class GameFragment : Fragment(R.layout.fragment_game) {

    val binding: FragmentGameBinding by viewBinding(FragmentGameBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}