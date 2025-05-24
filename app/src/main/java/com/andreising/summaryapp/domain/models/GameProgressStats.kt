package com.andreising.summaryapp.domain.models

data class GameProgressStats(
    val timeRemainInSeconds: Int,
    val timeProgressRemain: Float,
    val correctAnswersCount: Int,
    val totalAnswersCount: Int
) {
    companion object {
        fun getInitial() = GameProgressStats(0, 0f, 0, 0)
    }
}
