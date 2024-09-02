package eu.tutorials.attendancemanager.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tutorials.attendancemanager.data.remote.Subject
import eu.tutorials.attendancemanager.nav.Screen
import eu.tutorials.attendancemanager.presentation.components.CircularProgressBar
import eu.tutorials.attendancemanager.presentation.components.TopBar
import eu.tutorials.attendancemanager.presentation.viewmodel.MainViewModel
import eu.tutorials.attendancemanager.ui.theme.Purple
import java.security.AllPermission


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainView(navController: NavController, viewModel: MainViewModel) {

    val subjectList = viewModel.getAllSubjects.collectAsState(initial = listOf())
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() }
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 10.dp,
                backgroundColor = Color.Blue,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Target: 75%")
                        Text(text = "Total attendance: ${viewModel.totalAttendance}")
                    }
                    Button(
                        onClick = {
                            navController.navigate(Screen.AddSubject.route)
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "ADD SUBJECT")
                    }
                }

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(subjectList.value) { subject ->
                    SubjectCard(subject, viewModel)
                }
            }
        }
    }
}

@Composable
fun SubjectCard(subject: Subject, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(150.dp),
        elevation = 5.dp,
        backgroundColor = Color.Blue,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(text = subject.subjectName)
                Text(text = "Attendance: ${subject.present}/${subject.totalClasses}")
                Text(text = "Status: ")
            }
            Spacer(modifier = Modifier.weight(1f))
            CircularProgressBar(subject, viewModel)
        }

    }
}