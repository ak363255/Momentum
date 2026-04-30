/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.viewmodel

import com.example.api.navigation.HomeFeatureApi
import com.example.api.navigation.HomeFeatureStarterApi
import com.example.momentum.presentation.ui.tabs.views.TabBottomBarItems
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.platform.viemodel.BaseViewModel
import com.example.utils.platform.viemodel.work.WorkScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class TabScreenViewModel @Inject constructor(
    tabStateCommunicator: TabStateCommunicator,
    tabEffectCommunicator: TabEffectCommunicator,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val homeFeatureApi: HomeFeatureApi
) : BaseViewModel<TabScreenEvent, TabScreenActions, TabScreenEffect, TabViewState>(
    dispatcher = ioDispatcher,
    effectCommunicator = tabEffectCommunicator,
    stateCommunicator = tabStateCommunicator
) {
    override fun reduce(
        action: TabScreenActions,
        currentState: TabViewState
    ): TabViewState {
          return when(action){
              TabScreenActions.NavigateToAnalyticsTab -> currentState.copy(currentTab = TabBottomBarItems.ANALYTICS)
              TabScreenActions.NavigateToHomeTab -> currentState.copy(currentTab = TabBottomBarItems.HOME)
              TabScreenActions.NavigateToSettingsTab -> currentState.copy(currentTab = TabBottomBarItems.SETTING)
          }
    }

    override suspend fun WorkScope<TabViewState, TabScreenActions, TabScreenEffect>.handleEvent(
        event: TabScreenEvent
    ) {
        when (event) {
            TabScreenEvent.SelectMainScreen -> navigate(TabScreenActions.NavigateToHomeTab) {
               homeFeatureApi.getHomeFeatureStarter().navigateToHomeScreen()
            }

            TabScreenEvent.SelectedAnalyticsTab -> navigate(TabScreenActions.NavigateToAnalyticsTab) {

            }

            TabScreenEvent.SelectedCategories -> navigate(TabScreenActions.NavigateToHomeTab) {

            }

            TabScreenEvent.SelectedHomeTab -> navigate(TabScreenActions.NavigateToHomeTab) {

            }

            TabScreenEvent.SelectedOverviewScreen -> navigate(TabScreenActions.NavigateToHomeTab) {

            }

            TabScreenEvent.SelectedSettingTab -> navigate(TabScreenActions.NavigateToSettingsTab) {

            }

            TabScreenEvent.SelectedTemplateScreen -> navigate(TabScreenActions.NavigateToHomeTab) {

            }
        }

    }

    private suspend fun WorkScope<TabViewState, TabScreenActions, TabScreenEffect>.navigate(
        action: TabScreenActions,
        onAction: suspend () -> Unit
    ) {
        sendAction(action).also {
            onAction()
        }
    }

    private suspend fun WorkScope<TabViewState, TabScreenActions, TabScreenEffect>.onAction(effect: TabScreenEffect) {
        sendEffect(effect)
    }
}