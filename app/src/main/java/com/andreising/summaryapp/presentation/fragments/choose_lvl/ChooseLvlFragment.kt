package com.andreising.summaryapp.presentation.fragments.choose_lvl

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentChooseLvlBinding
import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.presentation.fragments.game.GameFragment
import com.andreising.summaryapp.presentation.navigation.setNewFragment
import dev.androidbroadcast.vbpd.viewBinding

class ChooseLvlFragment : Fragment(R.layout.fragment_choose_lvl) {

    private val binding: FragmentChooseLvlBinding by viewBinding(FragmentChooseLvlBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            testLvlButton.setOnClickListener { toGameFragment(Level.TEST) }
            easyLvlButton.setOnClickListener { toGameFragment(Level.EASY) }
            normalLvlButton.setOnClickListener { toGameFragment(Level.NORMAL) }
            hardLvlButton.setOnClickListener { toGameFragment(Level.HARD) }
        }
    }

    private fun toGameFragment(level: Level) {
        requireActivity().setNewFragment(
            fragment = GameFragment.newInstance(level),
            destinationName = GameFragment.NAME
        )
    }

    companion object {
        fun newInstance(): ChooseLvlFragment {
            return ChooseLvlFragment()
        }
    }

}