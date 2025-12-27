/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.impl.presentation.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.impl.presentation.theme.token.HomeIcons
import com.example.impl.presentation.theme.token.LocalHomeIcons
import com.example.impl.presentation.theme.token.LocalHomeStrings
import com.example.ui.theme.MomentumRes
import com.example.ui.views.TopAppBarButton
import com.example.ui.views.TopAppBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onMenuButtonClicked: () -> Unit,
    onOverviewButtonClicked: () -> Unit,
    onCalendarButtonClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            TopAppBarTitle(
                modifier = Modifier,
                text = LocalHomeStrings.current.topAppbarHomeTitle
            )
        },
        navigationIcon = {
            TopAppBarButton(
                icon = Icons.Default.Menu,
                onClick = onMenuButtonClicked,
            )
        },
        actions = {
            TopAppBarButton(
                icon = painterResource(LocalHomeIcons.current.calendar), // change to calendar icon,
                onClick = onCalendarButtonClicked
            )

            TopAppBarButton(
                icon = painterResource(MomentumRes.fetchAppIcons().overview), // change to overview icon,
                onClick = onOverviewButtonClicked
            )
        },
    )
}



