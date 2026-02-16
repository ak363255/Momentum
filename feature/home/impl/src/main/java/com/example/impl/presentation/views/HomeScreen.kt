/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.impl.presentation.views

import FeatureRootRoute
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.impl.common.HomePageRoutes
import com.example.impl.presentation.theme.HomeTheme
import com.example.impl.presentation.theme.token.LocalHomeStrings
import com.example.impl.presentation.viewmodel.HomeScreenViewModel
import com.example.impl.presentation.viewmodel.contract.HomeEvent
import com.example.impl.presentation.viewmodel.contract.HomeState
import com.example.module_injector.navigation.Navigable
import com.example.module_injector.navigation.OnNavigateTo
import com.example.ui.views.LocalRootNavigator
import com.example.utils.extensions.shiftDay
import com.example.utils.extensions.startThisDay
import com.example.utils.managers.LocalDrawerManager
import com.example.utils.platform.screen.ScreenContent
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


internal fun NavGraphBuilder.home(onNavigateTo: OnNavigateTo) {

    composable<FeatureRootRoute.HomeRootRoute> {
        val onNavigateTo = remember { onNavigateTo }
        val homeScreenViewmodel: HomeScreenViewModel = hiltViewModel()
        CompositionLocalProvider(LocalHomeNavigator provides onNavigateTo ) {
            HomeScreen(homeScreenViewModel = homeScreenViewmodel)
        }
    }

}

val LocalHomeNavigator = staticCompositionLocalOf<OnNavigateTo> { throw IllegalStateException() }

@Composable
internal fun HomeScreen(homeScreenViewModel: HomeScreenViewModel) {
    ScreenContent(homeScreenViewModel) { homeState ->
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
                HomeContent(
                    modifier = Modifier
                        .padding(paddingValues)
                        .background(color = MaterialTheme.colorScheme.surface),
                    homeViewState = homeState,
                    onChangeDate = { date ->
                        homeScreenViewModel.dispatchEvent(HomeEvent.LoadSchedule(date.startThisDay()))
                    },
                    onCreateSchedule = {
                          homeScreenViewModel.dispatchEvent(HomeEvent.CreateSchedule)
                    }
                )
            }
            HomeDatePicker(
                modifier = Modifier,
                onDismiss = {
                    showDateDialog.value = false
                },
                onDateSelected = { date ->
                    showDateDialog.value = false
                    homeScreenViewModel.dispatchEvent(HomeEvent.LoadSchedule(date.startThisDay()))
                },
                showDatePickerDialog = showDateDialog.value,
            )
        }
        LaunchedEffect(Unit) {
            dispatchEvent(HomeEvent.LoadSchedule(homeState.currentDate))
        }
    }


}

@Composable
internal fun HomeContent(
    modifier: Modifier = Modifier,
    homeViewState: HomeState,
    onChangeDate: (Date) -> Unit,
    onCreateSchedule: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        DateChooserSection(homeState = homeViewState, onChangeDate = onChangeDate)
        ScheduleSection(
            homeViewState, modifier = Modifier,
            onCreateSchedule = onCreateSchedule
        )
    }

}

@Composable
internal fun CalendarIcon(
    imageVector: ImageVector,
    onClick: () -> Unit = {},
    contentDescription: String?
) {
    Icon(
        imageVector = imageVector,
        modifier = Modifier
            .noRippleClickable { onClick() }
            .size(24.dp),
        contentDescription = contentDescription,
        tint = LocalContentColor.current
    )
}

@Composable
fun DateChooser(
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onChooseDate: () -> Unit,
    dateTitle: () -> String,
) {

    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .background(
                shape = RoundedCornerShape(percent = 50),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            )
            .height(36.dp)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.width(8.dp))
        CalendarIcon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onClick = onPrev,
            contentDescription = null,
        )
        Spacer(Modifier.width(24.dp))
        Text(
            modifier = Modifier.noRippleClickable {
                onChooseDate()
            },
            text = dateTitle(),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.width(24.dp))
        CalendarIcon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onClick = onNext,
            contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit) =
    this.then(this.clickable(indication = null, interactionSource = null) { onClick() })

@Composable
fun HomeDateChooser(
    currentDate: Date?,
    onDateSelected: (Date) -> Unit
) {
    val dateFormat = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
    var showDatePicker by remember { mutableStateOf(false) }
    DateChooser(
        onNext = {
            currentDate?.let { onDateSelected(it.shiftDay(1)) }
        },
        onPrev = {
            currentDate?.let { onDateSelected(it.shiftDay(-1)) }
        },
        onChooseDate = {
            showDatePicker = true
        },
        dateTitle = {
            currentDate?.let { dateFormat.format(it) } ?: ""
        }
    )
    HomeDatePicker(
        modifier = Modifier,
        onDismiss = {
            showDatePicker = false
        },
        onDateSelected = { date ->
            onDateSelected(date)
            showDatePicker = false
        },
        showDatePickerDialog = showDatePicker,
    )


}

@Composable
internal fun DateChooserSection(homeState: HomeState, onChangeDate: (Date) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        HomeDateChooser(
            currentDate = homeState.currentDate,
            onDateSelected = onChangeDate
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
internal fun ScheduleSection(homeState: HomeState, modifier: Modifier = Modifier,onCreateSchedule: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        HomeScheduleList(homeState)
        HomeEmptyScheduleView(homeState,onCreateSchedule = onCreateSchedule)
    }

}

@Composable
internal fun HomeScheduleList(homeState: HomeState) {
    if (homeState.timeTask.isNotEmpty()) {

    }
}


@Composable
internal fun HomeEmptyScheduleView(homeState: HomeState, modifier: Modifier = Modifier,onCreateSchedule: () -> Unit) {
    if (homeState.timeTask.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val homeStringRes = LocalHomeStrings.current
            EmptyScheduleView(emptyTitle = homeStringRes.emptyScheduleTitle, ctaText = homeStringRes.createScheduleTitle,onCreateSchedule)
        }

    }
}

@Composable
fun EmptyScheduleView(emptyTitle: String, ctaText: String,onCreateSchedule : ()-> Unit) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(com.example.impl.R.drawable.ic_not_found),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = emptyTitle,
            modifier = Modifier,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Spacer(Modifier.height(12.dp))
        SchedulePlanCta(modifier = Modifier, ctaText,onCreateSchedule)
    }
}

@Composable
internal fun SchedulePlanCta(modifier: Modifier, ctaText: String, onCreateSchedule: () -> Unit) {
    Row(
        modifier = modifier
            .noRippleClickable(onClick = onCreateSchedule)
            .padding(horizontal = 48.dp)
            .border(width = 1.dp,color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f), shape = RoundedCornerShape(percent = 50))
            .padding(horizontal = 32.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(com.example.impl.R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)

        )
        Spacer(Modifier.width(8.dp))
        Text(text = ctaText, style = MaterialTheme.typography.bodyMedium)
    }
}

