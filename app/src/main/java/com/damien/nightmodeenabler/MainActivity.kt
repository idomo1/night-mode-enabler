package com.damien.nightmodeenabler

import android.app.Service
import android.app.UiModeManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.damien.nightmodeenabler.components.App
import com.google.android.material.composethemeadapter3.Mdc3Theme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels {
            MainViewModelFactory(
                application,
                getSystemService(Service.UI_MODE_SERVICE) as UiModeManager
            )
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Mdc3Theme {
                App(viewModel) {
                    launchUrl(BuildConfig.PRIVACY_POLICY_URL)
                }
            }
        }
    }

    private fun launchUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
