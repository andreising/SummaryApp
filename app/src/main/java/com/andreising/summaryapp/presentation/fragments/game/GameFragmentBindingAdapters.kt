package com.andreising.summaryapp.presentation.fragments.game

import android.annotation.SuppressLint
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.andreising.summaryapp.R
import com.andreising.summaryapp.domain.models.GameProgressStats
import com.andreising.summaryapp.presentation.fragments.utils.setText
import com.andreising.summaryapp.presentation.fragments.utils.setTextWithFormat

@BindingAdapter("timer")
fun bindTimer(textView: TextView, timeInSec: Int) {
    setText(textView, formatSecondsToTime(timeInSec))
}

@BindingAdapter("sum_value")
fun bindSumValue(textView: TextView, value: Int) {
    setText(textView, value)
}

@BindingAdapter("first_operand")
fun bindFirstOperand(textView: TextView, firstOperand: Int) {
    setText(textView, firstOperand)
}

@BindingAdapter("progress_answers")
fun bindProgressAnswers(textView: TextView, gameProgressStats: GameProgressStats?) {
    if (gameProgressStats != null) setTextWithFormat(
        textView,
        R.string.progress_answers,
        gameProgressStats.correctAnswersCount,
        gameProgressStats.totalAnswersCount
    ) else {
        setTextWithFormat(textView, R.string.progress_answers, 0, 0)
    }
}

@BindingAdapter("progress_bar")
fun bindProgressBar(progressBar: ProgressBar, progress: Float) {
    progressBar.progress = (100 * progress).toInt()
}

@BindingAdapter("option")
fun bindOption(textView: TextView, value: Int) {
    setText(textView, value)
}

@SuppressLint("DefaultLocale")
private fun formatSecondsToTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}
