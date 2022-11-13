package com.damien.nightmodeenabler.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp

/**
 * An icon button which supports setting a long click listener.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LongClickIconButton(
    modifier: Modifier,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    contentDescription: String,
    background: Color,
    elevation: Dp,
    icon: Painter,
    iconSize: Dp,
    iconTint: Color
) {
    /**
     * Long press listener requires custom button implementation for ripple + elevation to work properly.
     */
    Surface(
        shadowElevation = elevation, shape = CircleShape, color = background, modifier = Modifier.then(modifier)
    ) {
        Box(
            modifier = Modifier.combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                role = Role.Button,
                onLongClick = onLongClick,
                onClick = onClick
            )
        ) {
            Icon(
                painter = icon, contentDescription = contentDescription, tint = iconTint, modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.Center)
            )
        }
    }
}
