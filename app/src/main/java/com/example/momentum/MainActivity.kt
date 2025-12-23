package com.example.momentum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.viewmodel.MainViewModel
import com.example.ui.theme.MomentumTheme
import com.example.ui.theme.tokens.LocalMomentumString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()
    var waitSplash: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                mainViewModel.effect.collect {
                    when (it) {
                        MainEffect.GoToMainPage -> {
                            waitSplash = false
                        }

                        MainEffect.DoNothing -> {}
                    }
                }
            }

            val mainViewState by mainViewModel.state.collectAsStateWithLifecycle()
            MomentumTheme(
                languageUiType = mainViewState.language,
                themeUiType = mainViewState.theme,
                colorsUiType = mainViewState.color
            ) {
                val coreString = LocalMomentumString.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                    ) {
                        Button(onClick = {}) {
                            Greeting(
                                name = "${coreString.appName}",
                                modifier = Modifier
                            )
                        }
                    }
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
