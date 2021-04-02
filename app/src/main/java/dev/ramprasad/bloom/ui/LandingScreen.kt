package dev.ramprasad.bloom.ui

import dev.ramprasad.bloom.ui.theme.MyTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.ramprasad.bloom.R

private const val SplashWaitTimeInMillis: Long = 2000

@Composable
fun LandingScreen(onTimeout : () -> Unit)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ){
        val rememberUpdatedState by rememberUpdatedState(newValue = onTimeout)
        LaunchedEffect(true){
            delay(SplashWaitTimeInMillis)
            rememberUpdatedState()
        }

        if(MaterialTheme.colors.isLight){
            Image(painterResource(id = R.drawable.ic_light_welcome_illos), contentDescription = null)
        }
        else{
            Image(painterResource(id = R.drawable.ic_dark_welcome_illos), contentDescription = null)
        }
    }
}

@Preview(showBackground = false, showSystemUi = false, device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL, widthDp = 360, heightDp = 640, name = "BasePreview"
)
@Composable
fun PreviewLightSplashScreen(){
    MyTheme(false) {
        LandingScreen {

        }
    }
}

@Preview(showBackground = false, showSystemUi = false, device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL, widthDp = 360, heightDp = 640, name = "BasePreview"
)
@Composable
fun PreviewDarkSplashScreen(){
    MyTheme(true) {
        LandingScreen {

        }
    }
}
