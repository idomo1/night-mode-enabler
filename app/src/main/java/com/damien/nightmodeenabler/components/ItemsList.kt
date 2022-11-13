package com.damien.nightmodeenabler.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

data class ListItemModel(val icon: Painter, val text: String, val onClick: () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsList(items: List<ListItemModel>) {
    Column(modifier = Modifier.navigationBarsPadding()) {
        items.forEach {
            ListItem(
                headlineText = { Text(it.text) },
                leadingContent = { Icon(it.icon, null) },
                modifier = Modifier.clickable { it.onClick() }
            )
        }
    }
}
