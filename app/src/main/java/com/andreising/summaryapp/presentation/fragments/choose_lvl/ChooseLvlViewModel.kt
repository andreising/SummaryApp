package com.andreising.summaryapp.presentation.fragments.choose_lvl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreising.summaryapp.domain.game_process.usecases.start.ChooseLevelUseCase
import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.presentation.fragments.utils.Event

class ChooseLvlViewModel(
    private val chooseLevelUseCase: ChooseLevelUseCase
) : ViewModel() {

    private val _navigateToGame = MutableLiveData<Event<Level>>()
    val navigateToGame: LiveData<Event<Level>> get() = _navigateToGame

    fun onLevelChose(level: Level) {
        chooseLevelUseCase.invoke(level)
        _navigateToGame.value = Event(level)
    }
}