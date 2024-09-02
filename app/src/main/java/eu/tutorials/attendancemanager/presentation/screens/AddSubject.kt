package eu.tutorials.attendancemanager.presentation.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tutorials.attendancemanager.presentation.viewmodel.MainViewModel
import eu.tutorials.attendancemanager.R
import eu.tutorials.attendancemanager.data.remote.Subject
import eu.tutorials.attendancemanager.nav.Screen
import eu.tutorials.attendancemanager.presentation.components.TopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddSubject(viewModel: MainViewModel, navController: NavController) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val greenColor = ButtonDefaults.buttonColors(Color(0xFF38c947))

    Scaffold(
        topBar = { TopBar() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 8.dp),
            elevation = 10.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp)
        ) {

            Column(modifier = Modifier
                .padding(it)
                .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Add Subject")
                SubjectTextField(
                    label = "Subject Name",
                    value = viewModel.subjectNameState,
                    onValueChanged = {
                                     viewModel.onSubjectNameChanged(it)
                    },
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                SubjectTextField(
                    label = "Present",
                    value = viewModel.presentState.toString(),
                    onValueChanged = {
                        viewModel.onPresentChanged(it.toInt())
                    },
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.height(10.dp))

                SubjectTextField(
                    label = "Total Classes",
                    value = viewModel.totalClassesState.toString(),
                    onValueChanged = {
                        viewModel.onTotalClassesChanged(it.toInt())
                    },
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Button(onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    }) {
                        Text(text = "CANCEL", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            if (viewModel.subjectNameState.isNotEmpty()) {
                                viewModel.addSubject(
                                    Subject(
                                        subjectName = viewModel.subjectNameState.trim(),
                                        present = viewModel.presentState,
                                        totalClasses = viewModel.totalClassesState
                                    )
                                )
                            } else {
                                Toast.makeText(context, "Enter name of the subject", Toast.LENGTH_SHORT).show()
                            }
                            navController.navigate(Screen.HomeScreen.route)
                        },
                        colors = greenColor
                    ) {
                        Text(
                            text = "ADD",
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

            }
        }
    }
}

@Composable
fun SubjectTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType
) {

    OutlinedTextField(
        value = value,
        onValueChange =onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )
    )
}

