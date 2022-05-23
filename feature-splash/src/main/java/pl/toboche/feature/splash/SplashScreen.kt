package pl.toboche.feature.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
    drawable: Int,
    viewModel: SplashScreenViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = drawable),
            contentDescription = "Loading your data, please wait",
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        CircularProgressIndicator(
            color = Color.Green,
            modifier = Modifier
                .size(100.dp)
        )
    }
}