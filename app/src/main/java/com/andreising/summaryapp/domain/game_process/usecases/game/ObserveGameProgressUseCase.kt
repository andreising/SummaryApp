package com.andreising.summaryapp.domain.game_process.usecases.game

import com.andreising.summaryapp.domain.game_process.repository.GameGateway

class ObserveGameProgressUseCase(
    private val gateway: GameGateway
) {
    operator fun invoke() = gateway.observeGameProgress()
}