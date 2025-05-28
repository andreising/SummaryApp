package com.andreising.summaryapp.presentation.fragments.choose_lvl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andreising.summaryapp.SummaryApp
import com.andreising.summaryapp.presentation.fragments.utils.viewModelProviderError

@Suppress("UNCHECKED_CAST")
class ChooseLvlViewModelFactory(private val app: SummaryApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseLvlViewModel::class.java)) {
            return ChooseLvlViewModel(chooseLevelUseCase = app.chooseLevelUseCase) as T
        }
        viewModelProviderError
    }
}