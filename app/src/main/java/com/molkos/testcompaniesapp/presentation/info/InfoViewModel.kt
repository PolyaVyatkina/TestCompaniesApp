package com.molkos.testcompaniesapp.presentation.info

import androidx.lifecycle.ViewModel
import com.molkos.testcompaniesapp.domain.model.CompanyInfo
import com.molkos.testcompaniesapp.domain.model.Response
import com.molkos.testcompaniesapp.domain.usecase.GetCompanyInfoUseCase
import com.molkos.testcompaniesapp.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InfoViewModel(
    private val getCompanyInfoUseCase: GetCompanyInfoUseCase,
) : ViewModel() {

    private val _info: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val info: StateFlow<UiState> = _info

    suspend fun loadInfo(companyId: Int) {
        _info.value = UiState.Loading
        val res: Response<CompanyInfo> = getCompanyInfoUseCase.execute(companyId)
        when (res) {
            is Response.Success -> _info.value = UiState.Success(res.body)
            is Response.Error -> _info.value = UiState.Error(res.error)
        }
    }
}