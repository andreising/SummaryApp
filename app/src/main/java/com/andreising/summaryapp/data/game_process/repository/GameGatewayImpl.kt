package com.andreising.summaryapp.data.game_process.repository

import com.andreising.summaryapp.data.game_process.source.GameManager
import com.andreising.summaryapp.domain.game_process.repository.GameGateway
import com.andreising.summaryapp.domain.models.Level

class GameGatewayImpl(private val gameManager: GameManager) : GameGateway {

    override fun chooseLevel(level: Level) = gameManager.initGame(level)

    override fun observeGameProgress() = gameManager.getGameCurrentStatsObserver()

    override fun observeCurrentQuestion() = gameManager.getQuestionObserver()

    override fun startGame() = gameManager.startGame()

    override fun sendAnswer(answer: Int) = gameManager.answerTheQuestion(answer)

    override fun observeGameEnd() = gameManager.getGameEndObserver()

    override fun cancelGame() = gameManager.cancelGame()

}