package com.andreising.summaryapp.presentation.fragments.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentWelcomeBinding
import dev.androidbroadcast.vbpd.viewBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    val binding: FragmentWelcomeBinding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.startGameButton.setOnClickListener {
            navigateToChooseLvlFragment()
        }
    }

    private fun navigateToChooseLvlFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_chooseLvlFragment)
    }

}