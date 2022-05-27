package pl.toboche.spacexlauchpadsbrowser.core.navigation

sealed class LaunchPadScreens(val route: String) {
    object ListScreen : LaunchPadScreens("launch_pads_list")
    object DetailsScreen : LaunchPadScreens("launch_pad_details")
}
