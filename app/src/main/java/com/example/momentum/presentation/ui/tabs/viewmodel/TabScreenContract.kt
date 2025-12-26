/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.viewmodel

import com.example.momentum.presentation.ui.tabs.views.TabBottomBarItems
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState

data class TabViewState(val currentTab : TabBottomBarItems = TabBottomBarItems.HOME) : BaseViewState

sealed class TabScreenEvent : BaseEvent{
    data object SelectedHomeTab : TabScreenEvent()
    data object SelectedAnalyticsTab : TabScreenEvent()
    data object SelectedSettingTab : TabScreenEvent()
    data object SelectMainScreen : TabScreenEvent()
    data object SelectedOverviewScreen : TabScreenEvent()
    data object SelectedTemplateScreen : TabScreenEvent()
    data object SelectedCategories : TabScreenEvent()
}

sealed class TabScreenActions : BaseAction{
    data object NavigateToHomeTab : TabScreenActions()
    data object NavigateToAnalyticsTab : TabScreenActions()
    data object NavigateToSettingsTab : TabScreenActions()
}

sealed class TabScreenEffect : BaseEffect{
    data object ShowHomeFeature : TabScreenEffect()
    data object ShowAnalyticsFeature : TabScreenEffect()
    data object ShowSettingsFeature : TabScreenEffect()
    data object ShowTemplateFeature : TabScreenEffect()
    data object ShowOverviewFeature : TabScreenEffect()
    data object ShowCategoriesFeature : TabScreenEffect()
}



