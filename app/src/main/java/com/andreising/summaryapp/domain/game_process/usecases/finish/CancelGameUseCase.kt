package com.andreising.summaryapp.domain.game_process.usecases.finish

import com.andreising.summaryapp.domain.game_process.repository.GameGateway

class CancelGameUseCase(
    private val gateway: GameGateway
) {
    operator fun invoke() = gateway.cancelGame()
}