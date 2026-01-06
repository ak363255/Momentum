/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.impl.presentation.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.impl.presentation.theme.HomeTheme
import com.example.impl.presentation.viewmodel.HomeScreenViewModel
import com.example.module_injector.navigation.NavigableRoutes
import com.example.module_injector.navigation.OnNavigateTo
import com.example.utils.managers.LocalDrawerManager
import kotlinx.coroutines.launch


internal fun NavGraphBuilder.home(navHostController: NavHostController) {
    composable<NavigableRoutes.MainPage> {
        val homeScreenViewmodel  : HomeScreenViewModel = hiltViewModel()
        HomeScreen() { destination, navOption ->
            navHostController.navigate(destination, navOption)
        }
    }
}

@Composable
internal fun HomeScreen(onNavigateTo: OnNavigateTo) {
    HomeTheme {
        val drawerManager = LocalDrawerManager.current
        val scope = rememberCoroutineScope()
        val showDateDialog = remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                HomeTopBar(
                    modifier = Modifier,
                    onMenuButtonClicked = { scope.launch { drawerManager.openDrawer() } },
                    onCalendarButtonClicked = {
                        showDateDialog.value = true
                    },
                    onOverviewButtonClicked = {}
                )
            }
        ) { paddingValues ->
            HomeContent(modifier = Modifier.padding(paddingValues), onNavigateTo = onNavigateTo)
        }

        HomeDatePicker(
            modifier = Modifier,
            onDismiss = {
                showDateDialog.value = false
            },
            onDateSelected = { date ->
                showDateDialog.value = false
            },
            showDatePickerDialog = showDateDialog.value,
        )
    }
}

@Composable
internal fun HomeContent(onNavigateTo: OnNavigateTo, modifier: Modifier = Modifier) {

}