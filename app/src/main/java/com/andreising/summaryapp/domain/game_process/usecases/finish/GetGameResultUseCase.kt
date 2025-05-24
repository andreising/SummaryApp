package com.andreising.summaryapp.domain.game_process.usecases.finish

import com.andreising.summaryapp.domain.game_process.repository.GameGateway

class GetGameResultUseCase(
    private val repository: GameGateway
) {
    operator fun invoke() = repository.getGameResult()
}