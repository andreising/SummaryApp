package com.andreising.summaryapp.domain.game_process.usecases.game

import com.andreising.summaryapp.domain.game_process.repository.GameGateway

class SendAnswerUseCase(
    private val gateway: GameGateway
) {
    operator fun invoke(answer: Int) = gateway.sendAnswer(answer)
}