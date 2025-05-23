package com.andreising.summaryapp.domain.game_process.usecases.game

import com.andreising.summaryapp.domain.game_process.repository.GameRepository

class GetCurrentQuestionLiveDataUseCase(
    private val repository: GameRepository
) {
    operator fun invoke() = repository.getCurrentQuestionLiveData()
}