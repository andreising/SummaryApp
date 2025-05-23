package com.andreising.summaryapp.domain.game_process.repository

import androidx.lifecycle.LiveData
import com.andreising.summaryapp.domain.models.GameProgressStats
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.domain.models.Question

interface GameRepository {

    //start
    fun chooseLevelAndStartGame(level: Level)

    //game process
    fun getGameCurrentStatisticLiveData(): LiveData<GameProgressStats>
    fun getCurrentQuestionLiveData(): LiveData<Question>
    fun sendAnswer(answer: Int)
    fun getGameEndObserver(): LiveData<Unit>

    //end
    fun getGameResult(): GameResult
}