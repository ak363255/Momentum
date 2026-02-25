/**
 * @author Amit Kumar on 25/02/26
 */

package com.example.ui.views

import androidx.compose.runtime.Composable
import com.example.ui.theme.tokens.CoreStrings
import com.example.ui.theme.tokens.LocalMomentumString
import com.example.utils.extensions.toMinutesOrHoursString

@Composable
fun Long.toMinutesOrHourTitle() : String{
    val minSymbol = LocalMomentumString.current.minuteSymbol
    val hourSymbol = LocalMomentumString.current.hourSymbol
    return this.toMinutesOrHoursString(minSymbol,hourSymbol)
}