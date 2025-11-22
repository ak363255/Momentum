/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.momentum.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momentum.R
import com.example.momentum.ui.theme.APP_NAME
import com.example.momentum.ui.theme.Pink_EC3270
import kotlinx.coroutines.delay

@Composable
fun SplashView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Pink_EC3270, Pink_EC3270, Color.White)
                )
            ), contentAlignment = Alignment.Center
    ) {
        val showLogo = remember {
            mutableStateOf(false)
        }
        val showName = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(50)
            showLogo.value = true
            delay(100)
            showName.value = true
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                visible = showLogo.value, enter = fadeIn()
            ) {
                Image(
                    painter = painterResource(R.drawable.time_planner),
                    modifier = Modifier.size(62.dp),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            AnimatedVisibility(visible = showName.value, enter = expandHorizontally()) {
                Text(
                    text = APP_NAME,
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W500
                    )
                )
            }

        }
    }
}