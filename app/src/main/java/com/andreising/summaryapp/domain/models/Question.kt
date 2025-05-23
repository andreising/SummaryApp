package com.andreising.summaryapp.domain.models

data class Question(
    val sum: Int,
    val firstOperand: Int,
    val answersList: List<Int>
)
