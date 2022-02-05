package com.damien.nightmodeenabler

import android.app.Service
import android.app.UiModeManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.damien.nightmodeenabler.bottomsheet.SetThemeBottomDialogFragment
import com.damien.nightmodeenabler.databinding.ActivityMainBinding

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

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        val setThemeBottomSheet = SetThemeBottomDialogFragment()
        binding.toggleNightModeButton.setOnLongClickListener {
            setThemeBottomSheet.show(supportFragmentManager, SetThemeBottomDialogFragment.TAG)
            true
        }
    }
}
