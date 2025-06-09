package com.andreising.summaryapp.di

import com.andreising.summaryapp.data.game_process.repository.GameGatewayImpl
import com.andreising.summaryapp.data.game_process.source.GameManager
import com.andreising.summaryapp.data.game_process.source.GameTimer
import com.andreising.summaryapp.domain.game_process.repository.GameGateway
import com.andreising.summaryapp.domain.game_process.usecases.finish.CancelGameUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveCurrentQuestionUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveGameEndUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveGameProgressUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.SendAnswerUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.StartGameUseCase
import com.andreising.summaryapp.domain.game_process.usecases.start.ChooseLevelUseCase
import com.andreising.summaryapp.presentation.fragments.choose_lvl.ChooseLvlViewModel
import com.andreising.summaryapp.presentation.fragments.game.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Core
    single { GameTimer() }
    single { GameManager(get()) }
    single<GameGateway> { GameGatewayImpl(get()) }

// UseCases
    factory { ChooseLevelUseCase(get()) }
    factory { StartGameUseCase(get()) }
    factory { SendAnswerUseCase(get()) }
    factory { ObserveCurrentQuestionUseCase(get()) }
    factory { ObserveGameProgressUseCase(get()) }
    factory { ObserveGameEndUseCase(get()) }
    factory { CancelGameUseCase(get()) }

// ViewModels
    viewModel { ChooseLvlViewModel(get()) }
    viewModel {
        GameViewModel(
            observeCurrentQuestionUseCase = get(),
            observeGameEndUseCase = get(),
            observeGameProgressUseCase = get(),
            sendAnswerUseCase = get(),
            startGameUseCase = get(),
            cancelGameUseCase = get()
        )
    }
}