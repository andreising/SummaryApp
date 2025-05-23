package com.andreising.summaryapp.domain.game_process.usecases.finish

import com.andreising.summaryapp.domain.game_process.repository.GameRepository

class GetGameResultUseCase(
    private val repository: GameRepository
) {
    operator fun invoke() = repository.getGameResult()
}