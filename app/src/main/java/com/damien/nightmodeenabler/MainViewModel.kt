package com.damien.nightmodeenabler

import android.app.Application
import android.app.UiModeManager
import android.content.res.Configuration
import android.os.Build
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application, private val uiModeManager: UiModeManager) :
    AndroidViewModel(application) {

    val isNightMode: Boolean get() = isSystemNightModeEnabled()

    fun toggleNightMode() {
        if (isSystemNightModeEnabled()) setNotNightMode() else setNightMode()
    }

    fun setAutoNightMode() {
        setCarModeIfRequired()
        uiModeManager.nightMode = UiModeManager.MODE_NIGHT_AUTO
    }

    fun setNightMode() {
        setCarModeIfRequired()
        uiModeManager.nightMode = UiModeManager.MODE_NIGHT_YES
    }

    fun setNotNightMode() {
        setCarModeIfRequired()
        uiModeManager.nightMode = UiModeManager.MODE_NIGHT_NO
    }

    private fun setCarModeIfRequired() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            // On <= API 22, changes to night mode only take effect when car or desk mode is enabled.
            uiModeManager.enableCarMode(0)
        }
    }

    private fun isSystemNightModeEnabled(): Boolean =
        when (uiModeManager.nightMode) {
            UiModeManager.MODE_NIGHT_YES -> true
            UiModeManager.MODE_NIGHT_AUTO -> {
                // This can also be undefined, assume that that means not night.
                getApplication<Application>().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            }
            else -> false
        }
}
