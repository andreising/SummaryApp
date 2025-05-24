package com.andreising.summaryapp.domain.game_process.usecases.start

import com.andreising.summaryapp.domain.game_process.repository.GameGateway
import com.andreising.summaryapp.domain.models.Level

class ChooseLevelUseCase(private val gateway: GameGateway) {
    fun invoke(level: Level) = gateway.chooseLevel(level)
}