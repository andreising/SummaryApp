package com.andreising.summaryapp.domain.models

// first item in answersList is correct but domain layer get question with shuffled answer list
data class Question(
    val sum: Int,
    val firstOperand: Int,
    val answersList: List<Int>
) {
    companion object {
        fun getInitial() = Question(0, 0, emptyList())
    }
}
