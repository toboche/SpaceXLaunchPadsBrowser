package pl.toboche.spacexlauchpadsbrowser.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details.ui.LaunchPadDetailsScreen
import pl.toboche.spacexlauchpadsbrowser.feature.launch_pads.ui.LaunchPadsListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LaunchPadScreens.ListScreen.route
    ) {
        composable(route = LaunchPadScreens.ListScreen.route) {
            LaunchPadsListScreen(navController)
        }
        composable(
            route = LaunchPadScreens.DetailsScreen.route + "/{launchPadId}",
            arguments = listOf(navArgument(name = "launchPadId") {
                type = NavType.LongType
            })
        ) {
            LaunchPadDetailsScreen(
                navController
            )
        }
    }

}