package com.andreising.summaryapp.presentation.fragments.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andreising.summaryapp.SummaryApp
import com.andreising.summaryapp.presentation.fragments.utils.viewModelProviderError

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(private val app: SummaryApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(app) {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) return GameViewModel(
            observeCurrentQuestionUseCase = observeQuestionUseCase,
            observeGameEndUseCase = observeEndUseCase,
            observeGameProgressUseCase = observeProgressUseCase,
            sendAnswerUseCase = sendAnswerUseCase,
            startGameUseCase = startGameUseCase,
            cancelGameUseCase = cancelGameUseCase
        ) as T
        viewModelProviderError
    }
}