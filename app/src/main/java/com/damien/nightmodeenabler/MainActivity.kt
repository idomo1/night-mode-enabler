package com.damien.nightmodeenabler

import android.app.Service
import android.app.UiModeManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
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

        binding.toobar.title = ""
        setSupportActionBar(binding.toobar)

        // Ensure that toolbar is not overlapping status bar.
        ViewCompat.setOnApplyWindowInsetsListener(binding.toobar) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
                leftMargin = insets.left
                rightMargin = insets.right
            }

            WindowInsetsCompat.CONSUMED
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.privacy_policy) {
            launchUrl(BuildConfig.PRIVACY_POLICY_URL)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun launchUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
