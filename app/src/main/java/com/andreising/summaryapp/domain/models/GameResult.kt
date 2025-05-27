package com.andreising.summaryapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val totalAnswers: Int,
    val correctAnswers: Int,
    val requiredCorrectAnswers: Int,
    val totalTimeSec: Int
) : Parcelable
