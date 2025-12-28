package com.example.momentum

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.momentum.di.modules.TabFeatureProvider
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenViewModel
import com.example.momentum.presentation.ui.tabs.views.MainScreen
import com.example.momentum.presentation.viewmodel.MainViewModel
import com.example.ui.theme.MomentumTheme
import com.example.utils.platform.screen.ScreenContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tabFeatureProvider: TabFeatureProvider

    val tabScreenViewModel : TabScreenViewModel by viewModels()
    val mainViewModel: MainViewModel by viewModels()
    var waitSplash: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().setKeepOnScreenCondition {
             waitSplash
        }
        setContent {
            ScreenContent(contractProvider = mainViewModel) { mainViewState ->
                MomentumTheme(
                    languageUiType = mainViewState.language,
                    themeUiType = mainViewState.theme,
                    colorsUiType = mainViewState.color
                ) {
                    MainScreen(tabFeatureProvider = tabFeatureProvider)
                    collectEffect { mainEffect ->
                        when (mainEffect) {
                            MainEffect.GoToMainPage -> {
                                Log.d("WORK","called go to main page")
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

