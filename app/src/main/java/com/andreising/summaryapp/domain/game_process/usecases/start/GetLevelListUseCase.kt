package com.andreising.summaryapp.domain.game_process.usecases.start

import com.andreising.summaryapp.domain.models.Level

class GetLevelListUseCase {
    fun invoke(): List<Level> {
        return Level.entries
    }
}