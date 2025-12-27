/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val SIZE = 48.dp
val SHAPE = CircleShape
val disableOpacity = 0.38f

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExtendedIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onDoubleClick: () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
    enable: Boolean = true
) {

    val containerColor = Color.Transparent
    val contentColor = LocalContentColor.current
    val disableColor = contentColor.copy(alpha = disableOpacity)
    val backgroundColor = if (enable) containerColor else disableColor
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .size(SIZE)
            .clip(SHAPE)
            .background(backgroundColor)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                onDoubleClick = onDoubleClick,
                interactionSource = interactionSource,
                enabled = enable,
                indication = LocalIndication.current
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }

}