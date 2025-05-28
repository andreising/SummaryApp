package com.andreising.summaryapp.presentation.fragments.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreising.summaryapp.domain.game_process.usecases.finish.CancelGameUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveCurrentQuestionUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveGameEndUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.ObserveGameProgressUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.SendAnswerUseCase
import com.andreising.summaryapp.domain.game_process.usecases.game.StartGameUseCase
import com.andreising.summaryapp.domain.models.GameProgressStats
import com.andreising.summaryapp.domain.models.Question
import com.andreising.summaryapp.presentation.fragments.utils.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GameViewModel(
    private val observeCurrentQuestionUseCase: ObserveCurrentQuestionUseCase,
    private val observeGameEndUseCase: ObserveGameEndUseCase,
    private val observeGameProgressUseCase: ObserveGameProgressUseCase,
    private val sendAnswerUseCase: SendAnswerUseCase,
    private val startGameUseCase: StartGameUseCase,
    private val cancelGameUseCase: CancelGameUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _questionLiveData = MutableLiveData<Question>()
    val questionLiveData: LiveData<Question> = _questionLiveData

    private val _progressLiveData = MutableLiveData<GameProgressStats>()
    val progressLiveData: LiveData<GameProgressStats> = _progressLiveData

    private val _gameEndLiveData = MutableLiveData<Event<Unit>>()
    val gameEndLiveData: LiveData<Event<Unit>> = _gameEndLiveData

    private fun subscribeToQuestionStream() {
        val disposable = observeCurrentQuestionUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { question ->
                _questionLiveData.value =
                    question.copy(answersList = question.answersList.shuffled())
            }
        disposables.add(disposable)
    }

    private fun subscribeToProgress() {
        val disposable = observeGameProgressUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { progress -> _progressLiveData.value = progress }
        disposables.add(disposable)
    }

    private fun subscribeToGameEnd() {
        val disposable = observeGameEndUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _gameEndLiveData.value = Event(Unit) }
        disposables.add(disposable)
    }

    fun sendAnswer(answer: Int) {
        sendAnswerUseCase(answer)
    }

    fun startGame() {
        subscribeToQuestionStream()
        subscribeToProgress()
        subscribeToGameEnd()
        startGameUseCase.invoke()
    }

    fun cancelGame() {
        cancelGameUseCase.invoke()
    }

    override fun onCleared() {
        disposables.clear()
    }
}