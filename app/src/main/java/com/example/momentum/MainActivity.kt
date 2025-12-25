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
import androidx.compose.ui.Modifier
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.viewmodel.MainViewModel
import com.example.ui.theme.MomentumTheme
import com.example.ui.theme.tokens.LocalMomentumString
import com.example.utils.platform.screen.ScreenContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()
    var waitSplash: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ScreenContent(contractProvider = mainViewModel) { mainViewState ->
                MomentumTheme(
                    languageUiType = mainViewState.language,
                    themeUiType = mainViewState.theme,
                    colorsUiType = mainViewState.color
                ) {
                    val coreString = LocalMomentumString.current
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            Button(onClick = {}) {
                                Greeting(
                                    name = coreString.appName,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                    collectEffect { mainEffect ->
                        when (mainEffect) {
                            MainEffect.GoToMainPage -> {
                                waitSplash = false
                            }

                            MainEffect.DoNothing -> {}
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
