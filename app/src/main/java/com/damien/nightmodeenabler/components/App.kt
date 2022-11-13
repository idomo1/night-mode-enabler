package com.damien.nightmodeenabler.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.damien.nightmodeenabler.MainViewModel
import com.damien.nightmodeenabler.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun App(viewModel: MainViewModel, openPrivacyPolicy: () -> Unit) {
    val menuItems = listOf(OverflowMenuItemModel(stringResource(R.string.privacy_policy_label), openPrivacyPolicy))

    Scaffold(
        topBar = {
            OverflowToolbar(
                title = "",
                menuItems = menuItems,
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.0f))
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { _ ->
        val bottomSheetState = rememberModalBottomSheetState(
            initialValue = Hidden,
            skipHalfExpanded = true
        )
        val scope = rememberCoroutineScope()
        val bottomSheetItems = listOf(
            ListItemModel(painterResource(id = R.drawable.ic_outline_wb_sunny_24), stringResource(id = R.string.set_day_mode)) {
                viewModel.setNotNightMode()
            },
            ListItemModel(painterResource(id = R.drawable.ic_dark_mode_24dp), stringResource(id = R.string.set_night_mode)) {
                viewModel.setNightMode()
            },
            ListItemModel(painterResource(id = R.drawable.ic_outline_autorenew_24), stringResource(id = R.string.set_auto_mode)) {
                viewModel.setAutoNightMode()
            }
        )
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetBackgroundColor = MaterialTheme.colorScheme.surface,
            sheetContent = { ItemsList(bottomSheetItems) }
        ) {
            Box(Modifier.fillMaxHeight()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_clouds),
                    contentDescription = null,
                    Modifier
                        .align(Alignment.TopCenter)
                        .height(400.dp)
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                )
                LongClickIconButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(96.dp)
                        .height(96.dp),
                    onClick = { viewModel.toggleNightMode() },
                    onLongClick = { scope.launch { bottomSheetState.show() } },
                    contentDescription =
                    if (viewModel.isNightMode) stringResource(R.string.disable_night_mode_cd)
                    else stringResource(R.string.enable_night_mode_cd),
                    background = MaterialTheme.colorScheme.secondary,
                    elevation = 10.dp,
                    icon = painterResource(id = R.drawable.ic_dark_mode_24dp),
                    iconSize = 88.dp,
                    iconTint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}
