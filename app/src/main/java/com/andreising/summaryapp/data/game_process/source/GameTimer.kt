package com.andreising.summaryapp.data.game_process.source

import android.os.CountDownTimer

class GameTimer {
    private var timer: CountDownTimer? = null

    fun setTimer(time: Int, onTickCallBack: (Int) -> Unit, onFinishCallback: () -> Unit) {
        timer = object : CountDownTimer(1000 * time.toLong(), INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                onTickCallBack((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                onFinishCallback()
            }
        }
    }

    fun startTimer() = timer?.start() ?: error("Timer not initialized")
    fun stopTimer() = timer?.cancel()

    companion object {
        private const val INTERVAL = 1000L
    }
}