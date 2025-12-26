/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenEvent
import com.example.ui.theme.MomentumRes
import com.example.ui.views.BottomBarItem
import com.example.ui.views.BottomNavigationBar


@Composable
fun TabBottomBar(
    selectedItem : TabBottomBarItems,
    modifier: Modifier = Modifier,
    onItemSelected : (TabScreenEvent)-> Unit

){
    BottomNavigationBar(
        selectedItem = selectedItem,
        tabs = TabBottomBarItems.values(),
        modifier = modifier,
        showLabel = true,
        onItemSelected = {items ->
            when(items){
                TabBottomBarItems.HOME -> onItemSelected(TabScreenEvent.SelectedHomeTab)
                TabBottomBarItems.ANALYTICS -> onItemSelected(TabScreenEvent.SelectedAnalyticsTab)
                TabBottomBarItems.SETTING -> onItemSelected(TabScreenEvent.SelectedSettingTab)
            }

        }
    )
}





enum class TabBottomBarItems : BottomBarItem{
    HOME{
        override val label: String
            @Composable get() = MomentumRes.fetchAppCoreStrings().homeTitle
        override val enableIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().enabledHomeIcon
        override val disableIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().disabledHomeIcon
    },

    ANALYTICS{
        override val label: String
        @Composable get() = MomentumRes.fetchAppCoreStrings().AnalyticsTitle
        override val enableIcon: Int
        @Composable get() = MomentumRes.fetchAppIcons().enabledAnalyticsIcon
        override val disableIcon: Int
        @Composable get() = MomentumRes.fetchAppIcons().disabledAnalyticsIcon
    },

    SETTING{
        override val label: String
            @Composable get() = MomentumRes.fetchAppCoreStrings().SettingsTitle
        override val enableIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().enabledSettingsIcon
        override val disableIcon: Int
            @Composable get() = MomentumRes.fetchAppIcons().disabledSettingsIcon
    }
}