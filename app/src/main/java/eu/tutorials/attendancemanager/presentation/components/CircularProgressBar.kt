package eu.tutorials.attendancemanager.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import eu.tutorials.attendancemanager.data.remote.Subject
import eu.tutorials.attendancemanager.presentation.viewmodel.MainViewModel

@Composable
fun CircularProgressBar(subject: Subject, viewModel: MainViewModel) {

    val curPercentage = subject.present.toFloat() / subject.totalClasses.toFloat()
    val sweepAngle = 360f * curPercentage

    val remainingPercentage = 1f - curPercentage
    val remainingSweepAngle = 360f * remainingPercentage

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .size(75.dp)
                    .padding(16.dp)
            ) {
                drawArc(
                    color = Color.Green,
                    startAngle = -90F,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(7.dp.toPx()),
                )

                drawArc(
                    color = Color.Red,
                    startAngle = sweepAngle - 90f,
                    sweepAngle = remainingSweepAngle,
                    useCenter = false,
                    style = Stroke(7.dp.toPx()),
                )
            }
        }
        Row {
            IconButton(onClick = {
                viewModel.onPresent(subject)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Check")
            }
            IconButton(onClick = {
                viewModel.onAbsent(subject)
            }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Check")
            }
        }
    }
}