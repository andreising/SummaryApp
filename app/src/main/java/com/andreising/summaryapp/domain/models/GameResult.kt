package com.andreising.summaryapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val totalAnswers: Int,
    val correctAnswers: Int,
    val requiredCorrectAnswers: Int,
    val totalTimeSec: Int
) : Parcelable {
    companion object {
        fun getInitial() = GameResult(0, 0, 0, 0)
        fun isInitial(gameResult: GameResult) = gameResult == getInitial()
    }
}
