/**
 * @author Amit Kumar on 16/02/26
 */

package com.example.impl.presentation.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ui.views.toMinutesOrHourTitle
import com.example.utils.extensions.duration
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun AddTimeTaskViewItem(
    startTime: Date,
    endTime: Date,
    onAddTimeTask : (startTime:Date,endTime: Date)-> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            StartTimeTaskTitle(startTime)
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Box(modifier = Modifier.padding(8.dp)) {
                    LinearProgressIndicator(
                        progress = { 0f },
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round,
                        gapSize = 0.dp,
                        drawStopIndicator = {}
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            border = BorderStroke(
                                1.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ), shape = RoundedCornerShape(size = 12.dp)
                        )
                        .clickable{
                            onAddTimeTask(startTime,endTime)
                        }
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = "Unassigned", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = duration(startTime,endTime).toMinutesOrHourTitle(), style = MaterialTheme.typography.labelMedium)
                }
            }

        }
    }
}

@Composable
fun StartTimeTaskTitle(startTime : Date){
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    Text(text = dateFormat.format(startTime), style = MaterialTheme.typography.titleMedium)
}

@Composable
@Preview
fun PreviewAddTimeTaskViewItem() {
    AddTimeTaskViewItem(startTime = Date(), endTime = Date()){startDate,endDate ->}
}