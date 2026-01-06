/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.views

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.MomentumRes
import com.example.ui.views.DrawerItems
import com.example.ui.views.DrawerLogoSection
import com.example.utils.managers.DrawerItem
import com.example.utils.managers.DrawerManager
import com.example.utils.managers.LocalDrawerManager
import kotlinx.coroutines.launch

@Composable
fun HomeNavigationDrawer(
    drawerState: DrawerState,
    drawerManager: DrawerManager,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onItemClicked: (HomeDrawerItems) -> Unit
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            DrawerContent(
                drawerManager = drawerManager,
                onItemClicked = { item ->
                    onItemClicked(item)
                }
            )
        },
        drawerState = drawerState,
    ) {
        CompositionLocalProvider(
            LocalDrawerManager provides drawerManager
        ) {
            content.invoke()
        }
    }
}

@Composable
fun DrawerContent(
    drawerManager: DrawerManager,
    onItemClicked: (HomeDrawerItems) -> Unit
) {
    val scope = rememberCoroutineScope()
    val selectedItemPosition by drawerManager.selectedItem.collectAsStateWithLifecycle()
    ModalDrawerSheet(modifier = Modifier.width(300.dp)){
        DrawerLogoSection(
            icon = MomentumRes.fetchAppIcons().logo,
            label = MomentumRes.fetchAppCoreStrings().appName
        )
        DrawerItems(
            drawerItems = HomeDrawerItems.values(),
            selectedItemPosition = selectedItemPosition,
            onDrawerItemClicked = { item,position ->
                onItemClicked(item)
                scope.launch {
                    drawerManager.closeDrawer()
                    drawerManager.selectedItem.value = position
                }
            }
        )
    }
}

enum class HomeDrawerItems : DrawerItem {
    MAIN {
        override val drawerIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().schedulerIcon
        override val drawerTitle: String
            @Composable get() = MomentumRes.fetchAppCoreStrings().mainDrawerTitle
    },
    OVERVIEW {
        override val drawerIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().overview
        override val drawerTitle: String
            @Composable get() = MomentumRes.fetchAppCoreStrings().overViewDrawerTitle
    },
    CATEGORIES {
        override val drawerIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().categoriesIcon
        override val drawerTitle: String
            @Composable get() = MomentumRes.fetchAppCoreStrings().categoriesDrawerTitle
    },
    TEMPLATE {
        override val drawerIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().template
        override val drawerTitle: String
            @Composable get() = MomentumRes.fetchAppCoreStrings().templateDrawerTitle
    }
}