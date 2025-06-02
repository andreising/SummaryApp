package com.andreising.summaryapp.presentation.fragments.finished

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.andreising.summaryapp.R
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.presentation.fragments.utils.setTextWithFormat

@BindingAdapter("required_answers")
fun bindRequiredAnswers(textView: TextView, requiredAnswers: Int) {
    setTextWithFormat(textView, R.string.required_answers, requiredAnswers)
}

@BindingAdapter("time_spent")
fun bindTimeSpent(textView: TextView, timeSpent: Int) {
    setTextWithFormat(textView, R.string.time_spent, timeSpent)
}

@BindingAdapter("total_right_answers")
fun bindTotalRightAnswers(textView: TextView, totalRightAnswers: Int) {
    setTextWithFormat(textView, R.string.right_answers, totalRightAnswers)
}

@BindingAdapter("result_image")
fun bindResultImage(imageView: ImageView, gameResult: GameResult) {
    imageView.setImageResource(
        if (gameResult.correctAnswers >= gameResult.requiredCorrectAnswers)
            R.drawable.ic_smile
        else
            R.drawable.ic_sad
    )
}