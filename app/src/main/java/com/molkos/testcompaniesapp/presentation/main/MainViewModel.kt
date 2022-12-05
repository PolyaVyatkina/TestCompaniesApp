package com.molkos.testcompaniesapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molkos.testcompaniesapp.domain.model.Company
import com.molkos.testcompaniesapp.domain.model.Response
import com.molkos.testcompaniesapp.domain.usecase.GetCompaniesListUseCase
import com.molkos.testcompaniesapp.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCompaniesListUseCase: GetCompaniesListUseCase,
) : ViewModel() {

    private val _companies: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val companies: StateFlow<UiState> = _companies

    init {
        loadCompanies()
    }

    fun loadCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            val res: Response<List<Company>> = getCompaniesListUseCase.execute()
            when (res) {
                is Response.Success -> _companies.value = UiState.Success(res.body)
                is Response.Error -> _companies.value = UiState.Error(res.error)
            }

        }
    }
}