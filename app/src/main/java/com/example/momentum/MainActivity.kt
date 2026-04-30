package com.example.momentum

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.module_injector.navigation.NavigationManager
import com.example.momentum.di.modules.FeatureEntryProvider
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
    lateinit var featureEntryProvider: FeatureEntryProvider
    @Inject lateinit var navigationManager : NavigationManager
    private lateinit var  navigator : Navigator
    val mainViewModel: MainViewModel by viewModels()

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
                    val showMainScreen = remember { mutableStateOf(false) }
                    collectEffect { mainEffect ->
                        when (mainEffect) {
                            MainEffect.GoToMainPage -> {
                                showMainScreen.value = true
                            }
                            MainEffect.DoNothing -> {}
                        }
                    }
                    if(showMainScreen.value){
                        MainScreen(featureEntry = featureEntryProvider,navigationManager)
                    }
                }
            }
        }
    }
}

