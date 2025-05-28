package com.andreising.summaryapp

import android.app.Application
import com.andreising.summaryapp.data.game_process.repository.GameGatewayImpl
import com.andreising.summaryapp.data.game_process.source.GameManager
import com.andreising.summaryapp.data.game_process.source.GameTimer
import com.andreising.summaryapp.domain.game_process.repository.GameGateway
import com.andreising.summaryapp.domain.game_process.usecases.finish.CancelGameUseCase
import com.andreising.summaryapp.domain.game_process.usecases.finish.GetGameResultUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveCurrentQuestionUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveGameEndUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveGameProgressUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.SendAnswerUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.StartGameUseCase
import com.andreising.summaryapp.domain.game_process.usecases.start.ChooseLevelUseCase

class SummaryApp : Application() {

    lateinit var gameManager: GameManager
    lateinit var gameGateway: GameGateway

    lateinit var chooseLevelUseCase: ChooseLevelUseCase
    lateinit var startGameUseCase: StartGameUseCase
    lateinit var sendAnswerUseCase: SendAnswerUseCase
    lateinit var observeQuestionUseCase: ObserveCurrentQuestionUseCase
    lateinit var observeProgressUseCase: ObserveGameProgressUseCase
    lateinit var observeEndUseCase: ObserveGameEndUseCase
    lateinit var getGameResultUseCase: GetGameResultUseCase
    lateinit var cancelGameUseCase: CancelGameUseCase

    override fun onCreate() {
        super.onCreate()

        // 1. Core
        gameManager = GameManager(GameTimer())
        gameGateway = GameGatewayImpl(gameManager)

        // 2. UseCases
        chooseLevelUseCase = ChooseLevelUseCase(gameGateway)
        startGameUseCase = StartGameUseCase(gameGateway)
        sendAnswerUseCase = SendAnswerUseCase(gameGateway)
        observeQuestionUseCase = ObserveCurrentQuestionUseCase(gameGateway)
        observeProgressUseCase = ObserveGameProgressUseCase(gameGateway)
        observeEndUseCase = ObserveGameEndUseCase(gameGateway)
        getGameResultUseCase = GetGameResultUseCase(gameGateway)
        cancelGameUseCase = CancelGameUseCase(gameGateway)
    }
}