package pl.toboche.spacexlauchpadsbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import pl.toboche.feature.splash.SplashScreen
import pl.toboche.spacexlauchpadsbrowser.ui.MainScreen
import pl.toboche.spacexlauchpadsbrowser.ui.theme.SpaceXLauchpadsBrowserTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXLauchpadsBrowserTheme {
                Scaffold(
                ) { padding ->
                    MainScreen(padding)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpaceXLauchpadsBrowserTheme {
        SplashScreen(R.drawable.ic_launcher_foreground)
    }
}