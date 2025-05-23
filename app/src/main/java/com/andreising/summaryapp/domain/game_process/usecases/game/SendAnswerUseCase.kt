package com.andreising.summaryapp.domain.game_process.usecases.game

import com.andreising.summaryapp.domain.game_process.repository.GameRepository

class SendAnswerUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(answer: Int) = repository.sendAnswer(answer)
}