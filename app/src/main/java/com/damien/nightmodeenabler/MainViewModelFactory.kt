package com.damien.nightmodeenabler

import android.app.Application
import android.app.UiModeManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(
    private val application: Application,
    private val uiModeManager: UiModeManager
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java, UiModeManager::class.java)
            .newInstance(application, uiModeManager)
    }
}
