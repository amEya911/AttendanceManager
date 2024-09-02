package eu.tutorials.attendancemanager.presentation.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import eu.tutorials.attendancemanager.R
import eu.tutorials.attendancemanager.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "Attendance Manager",
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
    )
}