package com.andreising.summaryapp.presentation.fragments.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andreising.summaryapp.R
import com.andreising.summaryapp.SummaryApp
import com.andreising.summaryapp.databinding.FragmentGameBinding
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.domain.models.Question
import com.andreising.summaryapp.presentation.fragments.finished.FinishGameFragment
import com.andreising.summaryapp.presentation.navigation.setNewFragment
import dev.androidbroadcast.vbpd.viewBinding

class GameFragment : Fragment(R.layout.fragment_game) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            GameViewModelFactory(requireActivity().application as SummaryApp)
        )[GameViewModel::class.java]
    }

    val binding: FragmentGameBinding by viewBinding(FragmentGameBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // region Observe
        observeQuestion()
        observeGameProgressStats()
        observeGameEnd()
        // endregion

        // region UI
        setOptionClick()
        startGame()
        // endregion
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.cancelGame()
    }

    private fun startGame() = viewModel.startGame()

    // region Observe

    private fun observeQuestion() {
        viewModel.questionLiveData.observe(viewLifecycleOwner) { question ->
            if (question == Question.getInitial()) return@observe
            binding.apply {
                tvSum.text = question.sum.toString()
                tvLeftNumber.text = question.firstOperand.toString()
                val optionViews =
                    listOf(tvOption1, tvOption2, tvOption3, tvOption4, tvOption5, tvOption6)
                question.answersList.forEachIndexed { i, answer ->
                    optionViews[i].text = answer.toString()
                }
            }
        }
    }

    private fun observeGameProgressStats() {
        viewModel.progressLiveData.observe(viewLifecycleOwner) { progress ->
            binding.apply {
                tvTimer.text = formatSecondsToTime(progress.timeRemainInSeconds)
                progressBar.progress = floatToIntPercent(progress.timeProgressRemain)
                tvAnswersProgress.text = String.format(
                    requireContext().getString(R.string.progress_answers),
                    progress.correctAnswersCount.toString(),
                    progress.totalAnswersCount.toString()
                )
            }
        }
    }

    private fun observeGameEnd() {
        viewModel.gameEndLiveData.observe(viewLifecycleOwner) { gameResult ->
            gameResult.getContentIfNotHandled()?.let {
                if (!GameResult.isInitial(it)) toFinishedFragment(it)
            }
        }
    }

    // endregion

    // region UI

    private fun floatToIntPercent(float: Float): Int {
        return (100 * float).toInt()
    }

    @SuppressLint("DefaultLocale")
    private fun formatSecondsToTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    private fun setOptionClick() = with(binding) {
        val clickHandler = View.OnClickListener { view ->
            val text = (view as TextView).text.toString()
            text.toIntOrNull()?.let { viewModel.sendAnswer(it) }
        }

        listOf(
            tvOption1, tvOption2, tvOption3,
            tvOption4, tvOption5, tvOption6
        ).forEach { it.setOnClickListener(clickHandler) }
    }

    private fun toFinishedFragment(gameResult: GameResult) {
        requireActivity().setNewFragment(FinishGameFragment.newInstance(gameResult))
    }

    //end region

    companion object {

        const val NAME = "game_fragment"

        fun newInstance(): GameFragment {
            return GameFragment()
        }
    }
}