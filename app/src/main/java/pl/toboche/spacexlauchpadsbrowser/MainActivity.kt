package pl.toboche.spacexlauchpadsbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import pl.toboche.spacexlauchpadsbrowser.ui.theme.SpaceXLauchpadsBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXLauchpadsBrowserTheme {
                // A surface container using the 'background' color from the theme
                Scaffold {
                    SplashScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Icon(
        painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Localized description",
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpaceXLauchpadsBrowserTheme {
        SplashScreen()
    }
}