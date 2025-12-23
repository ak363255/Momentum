/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.momentum.presentation.contract

import com.example.domain.models.settings.Setting
import com.example.ui.theme.materials.ColorsUiType
import com.example.ui.theme.materials.ThemeUiType
import com.example.ui.theme.tokens.LanguageUiType
import com.example.utils.di.annotations.UiDispatcher
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import javax.inject.Inject

sealed class MainEvent : BaseEvent {
    data object LoadSetting : MainEvent()
}

sealed class MainAction : BaseAction {
    data class ChangeSettings(
        val languageUiType: LanguageUiType,
        val colorsUiType: ColorsUiType,
        val themeUiType: ThemeUiType
    ) : MainAction()
}

sealed class MainEffect : BaseEffect {
    data object GoToMainPage : MainEffect()
    object DoNothing: MainEffect()
}

data class MainViewState @Inject constructor(
    val language: LanguageUiType = LanguageUiType.HINDI,
    val theme: ThemeUiType = ThemeUiType.DEFAULT,
    val color: ColorsUiType = ColorsUiType.PINK
) : BaseViewState