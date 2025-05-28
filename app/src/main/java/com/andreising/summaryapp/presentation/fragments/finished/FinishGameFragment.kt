package com.andreising.summaryapp.presentation.fragments.finished

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentFinishGameBinding
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.presentation.fragments.game.GameFragment
import com.andreising.summaryapp.presentation.navigation.popBackStack
import com.andreising.summaryapp.presentation.navigation.requireParcelable
import dev.androidbroadcast.vbpd.viewBinding

class FinishGameFragment : Fragment(R.layout.fragment_finish_game) {

    val binding: FragmentFinishGameBinding by viewBinding(FragmentFinishGameBinding::bind)

    private lateinit var gameResult: GameResult

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameResult = requireArguments().requireParcelable<GameResult>(GAME_RESULT_KEY)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        bindResult(gameResult)
    }

    private fun bindResult(result: GameResult) = with(binding) {
        resultImage.setImageResource(
            if (result.correctAnswers >= result.requiredCorrectAnswers)
                R.drawable.ic_smile
            else
                R.drawable.ic_sad
        )

        rightAnswersText.text = getString(R.string.right_answers, result.correctAnswers.toString())
        requiredAnswersText.text =
            getString(R.string.required_answers, result.requiredCorrectAnswers.toString())
        timeSpentText.text = getString(R.string.time_spent, result.totalTimeSec.toString())
    }


    private fun retryGame() {
        requireActivity().popBackStack(GameFragment.NAME, true)
    }

    companion object {

        private const val GAME_RESULT_KEY = "game_result_key"

        fun newInstance(gameResult: GameResult): FinishGameFragment {
            return FinishGameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT_KEY, gameResult)
                }
            }
        }
    }
}