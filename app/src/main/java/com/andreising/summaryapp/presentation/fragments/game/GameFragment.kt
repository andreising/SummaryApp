package com.andreising.summaryapp.presentation.fragments.game

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andreising.summaryapp.R
import com.andreising.summaryapp.SummaryApp
import com.andreising.summaryapp.databinding.FragmentGameBinding
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.domain.models.Question
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
            binding.question = question
        }
    }

    private fun observeGameProgressStats() {
        viewModel.progressLiveData.observe(viewLifecycleOwner) { progress ->
            binding.gameProgress = progress
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
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToFinishGameFragment2(
                gameResult
            )
        )
    }

    //end region
}