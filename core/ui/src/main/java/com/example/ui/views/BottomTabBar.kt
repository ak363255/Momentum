/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.ui.views

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

interface BottomBarItem{
    val label : String
        @Composable get
    val enableIcon : Int
        @Composable get
    val disableIcon : Int
        @Composable get
}

@Composable
fun <Item: BottomBarItem>BottomNavigationBar(
    selectedItem: Item,
    tabs : Array<Item>,
    modifier: Modifier = Modifier,
    showLabel : Boolean = true,
    onItemSelected : (Item)-> Unit
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ){
        tabs.forEach { item ->
            NavigationBarItem(
                selected = item == selectedItem,
                onClick = {onItemSelected(item)},
                icon = {
                    BottomBarIcon(
                        selected = selectedItem == item,
                        enabledIcon = painterResource(id = item.enableIcon),
                        disabledIcon = painterResource(id = item.disableIcon),
                        description = item.label
                    )
                },
                label = if(showLabel) {
                    {
                        BottomBarLabel(selected = selectedItem == item, title = item.label )
                    }
                } else {
                    null
                }
            )

        }

    }

}


@Composable
fun BottomBarIcon(
    selected: Boolean,
    enabledIcon: Painter,
    disabledIcon: Painter,
    description: String,
) {
    Icon(
        painter = if (selected) enabledIcon else disabledIcon,
        contentDescription = description,
        tint = when (selected) {
            true -> MaterialTheme.colorScheme.onSecondaryContainer
            false -> MaterialTheme.colorScheme.onSurfaceVariant
        },
    )
}

@Composable
fun BottomBarLabel(
    selected: Boolean,
    title: String,
) {
    Text(
        text = title,
        color = when (selected) {
            true -> MaterialTheme.colorScheme.onSurface
            false -> MaterialTheme.colorScheme.onSurfaceVariant
        },
        style = MaterialTheme.typography.labelMedium,
    )
}