package com.andreising.summaryapp.presentation.fragments.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentWelcomeBinding
import com.andreising.summaryapp.presentation.fragments.choose_lvl.ChooseLvlFragment
import com.andreising.summaryapp.presentation.navigation.setNewFragment
import dev.androidbroadcast.vbpd.viewBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    val binding: FragmentWelcomeBinding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.startGameButton.setOnClickListener {
            requireActivity().setNewFragment(
                fragment = ChooseLvlFragment.newInstance()
            )
        }
    }

    companion object {
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment()
        }
    }

}