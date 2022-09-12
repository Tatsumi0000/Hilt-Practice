package com.example.hilt_practice

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val useCase: SampleUseCase
    ) : ViewModel() {

    fun isGranted(): Boolean {
        return useCase.isGranted()
    }

}