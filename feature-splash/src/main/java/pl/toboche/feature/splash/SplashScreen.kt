package pl.toboche.feature.splash

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun SplashScreen(drawable: Int) {
    Icon(
        painterResource(id = drawable),
        contentDescription = "Localized description",
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}