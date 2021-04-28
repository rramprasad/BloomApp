package dev.ramprasad.bloom.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.ui.theme.BloomTheme

private const val LOG_TAG: String = "LandingScreen"

@Composable
fun LandingScreen(onTimeout : () -> Unit,)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val rememberedOnTimeout by rememberUpdatedState(newValue = onTimeout)
        val rememberedAppNameVisibility by rememberSaveable {
            mutableStateOf(false)
        }
        LoadLottieAnimation(rememberedOnTimeout)
        AppName(rememberedAppNameVisibility)
    }
}

@Composable
fun LoadLottieAnimation(rememberedOnTimeout: () -> Unit) {
    val animationSpec = remember {
        LottieAnimationSpec.RawRes(R.raw.bloom_animation)
    }

    val animationState = rememberLottieAnimationState(
        autoPlay = true,
        repeatCount = 0
    )
    animationState.speed = 1F

    LottieAnimation(
        spec = animationSpec,
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        animationState = animationState
    )

    if(!animationState.isPlaying){
        rememberedOnTimeout()
    }
}

@Composable
fun AppName(rememberedAppNameVisibility: Boolean) {
    Text(
        text = stringResource(id = R.string.app_name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.wrapContentSize()
    )
}

@Preview(showBackground = false, showSystemUi = false, device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL, widthDp = 360, heightDp = 640, name = "BasePreview"
)
@Composable
fun PreviewLightSplashScreen(){
    BloomTheme(false) {
        LandingScreen {

        }
    }
}

@Preview(showBackground = false, showSystemUi = false, device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL, widthDp = 360, heightDp = 640, name = "BasePreview"
)
@Composable
fun PreviewDarkSplashScreen(){
    BloomTheme(true) {
        LandingScreen {

        }
    }
}
