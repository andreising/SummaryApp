package com.andreising.summaryapp.domain.models

data class GameResult(
    val currentLevel: Level,
    val correctAnswers: Int,
    val requiredCorrectAnswers: Int,
    val totalTimeSec: Int
)
