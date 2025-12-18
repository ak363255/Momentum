package com.example.momentum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.viewmodel.MainViewModel
import com.example.ui.theme.MomentumTheme
import com.example.ui.theme.materials.ColorsUiType
import com.example.ui.theme.materials.ThemeUiType
import com.example.ui.theme.tokens.LanguageUiType

class MainActivity : ComponentActivity() {
    val mainViewModel : MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory()
    }
    var waitSplash : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                mainViewModel.effect.collect {
                    when(it){
                        MainEffect.GoToMainPage -> {
                            waitSplash = false
                        }
                    }
                }
            }

            val mainViewState = mainViewModel.state.collectAsStateWithLifecycle()
            MomentumTheme(
                languageUiType = LanguageUiType.ENGLISH,
                themeUiType = ThemeUiType.LIGHT,
                colorsUiType = ColorsUiType.RED
            ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
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
