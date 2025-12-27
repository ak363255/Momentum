/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.impl.presentation.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.impl.presentation.theme.token.LocalHomeStrings
import com.example.ui.theme.tokens.LocalMomentumString
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDatePicker(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onDateSelected: (Date) -> Unit,
    showDatePickerDialog: Boolean
) {
    if (showDatePickerDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnable by derivedStateOf {
            datePickerState.selectableDates != null
        }
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {}, enabled = confirmEnable
                ) {
                    Text(
                        text = LocalMomentumString.current.confirmTitle,
                    )
                }
            },
            modifier = modifier,
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = LocalMomentumString.current.cancelTitle
                    )
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier,
                title = {
                    Text(
                        text = LocalHomeStrings.current.datePickerTitle,
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                    )
                },
                headline = {
                    Text(
                        text = LocalHomeStrings.current.datePickerHeadline,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }

            )
        }
    }

}