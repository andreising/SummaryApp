package com.andreising.summaryapp.presentation.fragments.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentGameBinding
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.presentation.fragments.finished.FinishedFragment
import com.andreising.summaryapp.presentation.navigation.setNewFragment
import dev.androidbroadcast.vbpd.viewBinding

class GameFragment : Fragment(R.layout.fragment_game) {

    private lateinit var currentLevel: Level

    val binding: FragmentGameBinding by viewBinding(FragmentGameBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentLevel = Level.fromName(requireArguments().getString(LEVEL_NAME_KEY))
    }

    private fun toFinishedFragment(gameResult: GameResult) {
        requireActivity().setNewFragment(FinishedFragment.newInstance(gameResult))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        private const val LEVEL_NAME_KEY = "level_name_key"

        const val NAME = "game_fragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putString(LEVEL_NAME_KEY, level.name)
                }
            }
        }
    }
}