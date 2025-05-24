package com.andreising.summaryapp.domain.models

enum class Level(
    val totalTimeSec: Int,
    val maxValue: Int,
    val requiredCorrectAnswer: Int,
    val totalQuestion: Int
) {
    TEST(
        totalTimeSec = 8,
        maxValue = 10,
        requiredCorrectAnswer = 3,
        totalQuestion = 6
    ),
    EASY(
        totalTimeSec = 60,
        maxValue = 10,
        requiredCorrectAnswer = 10,
        totalQuestion = 20
    ),
    NORMAL(
        totalTimeSec = 40,
        maxValue = 20,
        requiredCorrectAnswer = 18,
        totalQuestion = 20
    ),
    HARD(
        totalTimeSec = 40,
        maxValue = 30,
        requiredCorrectAnswer = 20,
        totalQuestion = 20
    )
}