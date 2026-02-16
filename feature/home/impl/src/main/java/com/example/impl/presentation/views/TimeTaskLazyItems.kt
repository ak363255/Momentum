/**
 * @author Amit Kumar on 16/02/26
 */

package com.example.impl.presentation.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Date
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun AddTimeTaskViewItem(
    startTime : Date,
    endTime : Date
){

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 6.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()){
            Text(text = "11:50 pm")
            Spacer(modifier = Modifier.width(4.dp))
            LinearProgressIndicator(
                progress = {
                    0.5f
                },
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.surfaceVariant,
                trackColor = MaterialTheme.colorScheme.primary,
                strokeCap = StrokeCap.Square,
                gapSize = 0.dp
            )
        }
    }


}

@Composable
@Preview
fun PreviewAddTimeTaskViewItem(){
    AddTimeTaskViewItem(startTime = Date(), endTime = Date())
}