package com.damien.nightmodeenabler.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.damien.nightmodeenabler.R

data class OverflowMenuItemModel(val label: String, val onClick: () -> Unit)

/**
 * A toolbar which has an overflow menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverflowToolbar(
    title: String,
    menuItems: List<OverflowMenuItemModel>,
    colors: TopAppBarColors
) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, stringResource(R.string.overflow_menu_content_description))
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {
                menuItems.forEach { DropdownMenuItem({ Text(it.label) }, it.onClick) }
            }
        },
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        colors = colors
    )
}
