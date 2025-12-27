/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.utils.managers.DrawerItem

@Composable
fun DrawerLogoSection(
    icon: Int,
    label: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = color
        )
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge
        )

    }

}

@Composable
fun <Item : DrawerItem> DrawerItems(
    modifier: Modifier = Modifier,
    drawerItems: Array<Item>,
    selectedItemPosition: Int,
    onDrawerItemClicked: (Item, Int) -> Unit
) {
    drawerItems.forEachIndexed { index, item ->
        DrawerItem(
            isSelected = index == selectedItemPosition,
            modifier = modifier,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            label = {
                Text(
                    text = item.drawerTitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge,
                )
            },
            icon = {
                Icon(
                    painter = painterResource(item.drawerIcon), contentDescription = null,
                )
            },
            onClick = {
                onDrawerItemClicked(item, index)
            }
        )
    }

}

@Composable
fun DrawerItem(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    colors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(),
    label: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(end = 12.dp)
            .clickable(onClick = onClick),
        color = colors.containerColor(isSelected).value,
        shape = RoundedCornerShape(
            topEnd = 25.dp,
            bottomEnd = 25.dp
        )
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),

            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                val iconColor = colors.iconColor(isSelected).value
                CompositionLocalProvider(LocalContentColor provides iconColor) {
                    icon()
                }
            }
            if (label != null) {
                val labelColor = colors.textColor(isSelected).value
                CompositionLocalProvider(LocalContentColor provides labelColor) {
                    label()
                }
            }
            if (badge != null) {
                val badgeColor = colors.badgeColor(isSelected).value
                CompositionLocalProvider(LocalContentColor provides badgeColor) {
                    badge()
                }
            }
        }
    }
}