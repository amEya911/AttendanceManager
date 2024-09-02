package eu.tutorials.attendancemanager.nav

sealed class Screen(val route: String) {

    object HomeScreen: Screen("home_screen")
    object AddSubject: Screen("add_subject")
}