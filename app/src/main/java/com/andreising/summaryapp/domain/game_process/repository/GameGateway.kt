package com.andreising.summaryapp.domain.game_process.repository

import com.andreising.summaryapp.domain.models.GameProgressStats
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.domain.models.Question
import io.reactivex.rxjava3.core.Observable

interface GameGateway {

    //pre-start
    fun chooseLevel(level: Level)

    //start
    fun observeGameProgress(): Observable<GameProgressStats>
    fun observeCurrentQuestion(): Observable<Question>
    fun startGame()

    //game process
    fun sendAnswer(answer: Int)
    fun observeGameEnd(): Observable<GameResult>

    //end
    fun cancelGame()
}