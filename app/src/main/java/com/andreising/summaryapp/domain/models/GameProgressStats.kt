package com.andreising.summaryapp.domain.models

data class GameProgressStats(
    val timeRemainInSeconds: Int,
    val progressRemain: Float,
    val correctAnswersCount: Int,
    val totalAnswersCount: Int
)
