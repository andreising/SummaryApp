package com.andreising.summaryapp.presentation.fragments.finished

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentFinishedBinding
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.presentation.fragments.game.GameFragment
import com.andreising.summaryapp.presentation.navigation.popBackStack
import com.andreising.summaryapp.presentation.navigation.requireParcelable
import dev.androidbroadcast.vbpd.viewBinding

class FinishedFragment : Fragment(R.layout.fragment_finished) {

    val binding: FragmentFinishedBinding by viewBinding(FragmentFinishedBinding::bind)

    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameResult = requireArguments().requireParcelable<GameResult>(GAME_RESULT_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun retryGame() {
        requireActivity().popBackStack(GameFragment.NAME, true)
    }

    companion object {

        private const val GAME_RESULT_KEY = "game_result_key"

        fun newInstance(gameResult: GameResult): FinishedFragment {
            return FinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT_KEY, gameResult)
                }
            }
        }
    }
}