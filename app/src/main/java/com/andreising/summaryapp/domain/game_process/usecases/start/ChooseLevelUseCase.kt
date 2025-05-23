package com.andreising.summaryapp.domain.game_process.usecases.start

import com.andreising.summaryapp.domain.game_process.repository.GameRepository
import com.andreising.summaryapp.domain.models.Level

class ChooseLevelUseCase(private val repository: GameRepository) {
    fun invoke(level: Level) = repository.chooseLevelAndStartGame(level)
}