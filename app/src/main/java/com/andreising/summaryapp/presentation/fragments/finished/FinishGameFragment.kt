package com.andreising.summaryapp.presentation.fragments.finished

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreising.summaryapp.R
import com.andreising.summaryapp.databinding.FragmentFinishGameBinding
import com.andreising.summaryapp.domain.models.GameResult
import dev.androidbroadcast.vbpd.viewBinding

class FinishGameFragment : Fragment(R.layout.fragment_finish_game) {

    private val args by navArgs<FinishGameFragmentArgs>()

    val binding: FragmentFinishGameBinding by viewBinding(FragmentFinishGameBinding::bind)

    private val gameResult: GameResult by lazy { args.gameResult }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicks()
        bindResult(gameResult)
    }

    private fun bindResult(result: GameResult) {
        binding.gameResult = result
    }

    private fun setClicks() {
        binding.retryButton.setOnClickListener { findNavController().popBackStack() }
    }
}