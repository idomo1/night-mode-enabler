package com.damien.nightmodeenabler.tile

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class NightModeTileService : TileService() {

    private lateinit var uiModeManager: UiModeManager

    override fun onCreate() {
        super.onCreate()
        uiModeManager =
            applicationContext.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    }

    override fun onTileAdded() {
        super.onTileAdded()
        qsTile.state = when {
            isSystemNightModeEnabled() -> Tile.STATE_ACTIVE
            else -> Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        qsTile.state = if (isSystemNightModeEnabled()) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        if (isSystemNightModeEnabled()) {
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_NO
            qsTile.state = Tile.STATE_INACTIVE
        } else {
            uiModeManager.nightMode = UiModeManager.MODE_NIGHT_YES
            qsTile.state = Tile.STATE_ACTIVE
        }
        qsTile.updateTile()
    }

    private fun isSystemNightModeEnabled(): Boolean =
        when (uiModeManager.nightMode) {
            UiModeManager.MODE_NIGHT_YES -> true
            UiModeManager.MODE_NIGHT_AUTO -> {
                // This can also be undefined, assume that that means not night.
                applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            }
            else -> false
        }
}
