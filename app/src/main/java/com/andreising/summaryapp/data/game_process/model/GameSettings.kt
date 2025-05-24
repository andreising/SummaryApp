package com.andreising.summaryapp.data.game_process.model

import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.domain.models.Question

data class GameSettings(
    val currentLevel: Level,
    val questionList: List<Question>
)
