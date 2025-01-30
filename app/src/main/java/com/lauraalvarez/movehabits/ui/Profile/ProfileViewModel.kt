package com.lauraalvarez.movehabits.ui.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lauraalvarez.movehabits.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject
constructor(
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _logoutSuccess = MutableLiveData<Boolean>()
    val logoutSuccess: LiveData<Boolean> = _logoutSuccess

    fun logout() {
        logoutUseCase.execute()
        _logoutSuccess.value = true
    }
}
