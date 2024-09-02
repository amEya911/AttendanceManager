package eu.tutorials.attendancemanager.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.tutorials.attendancemanager.presentation.screens.AddSubject
import eu.tutorials.attendancemanager.presentation.screens.MainView
import eu.tutorials.attendancemanager.presentation.viewmodel.MainViewModel

@Composable
fun Navigation(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        composable(Screen.HomeScreen.route) {
            MainView(navController, viewModel)
        }

        composable(
            Screen.AddSubject.route,
        ){
            AddSubject(viewModel = viewModel , navController = navController)
        }
    }
}