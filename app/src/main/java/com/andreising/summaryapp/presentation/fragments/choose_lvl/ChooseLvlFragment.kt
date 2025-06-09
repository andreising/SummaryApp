package com.andreising.summaryapp.presentation.fragments.choose_lvl

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentChooseLvlBinding
import com.andreising.summaryapp.domain.models.Level
import dev.androidbroadcast.vbpd.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseLvlFragment : Fragment(R.layout.fragment_choose_lvl) {

    private val binding: FragmentChooseLvlBinding by viewBinding(FragmentChooseLvlBinding::bind)
    private val viewModel: ChooseLvlViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()
        initObserver()
    }

    private fun initButtons() {
        binding.apply {
            easyLvlButton.setOnClickListener { levelChose(Level.EASY) }
            normalLvlButton.setOnClickListener { levelChose(Level.NORMAL) }
            hardLvlButton.setOnClickListener { levelChose(Level.HARD) }
        }
    }

    private fun initObserver() {
        viewModel.navigateToGame.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { level ->
                navigateToGameFragment()
            }
        }
    }

    private fun navigateToGameFragment() {
        findNavController().navigate(R.id.action_chooseLvlFragment_to_gameFragment)
    }

    private fun levelChose(level: Level) {
        viewModel.onLevelChose(level)
    }

}