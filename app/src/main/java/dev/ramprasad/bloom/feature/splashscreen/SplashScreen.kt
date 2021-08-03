/*
 * Created by Ramprasad Ranganathan on 09/06/21, 8:57 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 09/06/21, 8:57 PM
 */

package dev.ramprasad.bloom.feature.splashscreen

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
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.theme.BloomTheme

private const val LOG_TAG: String = "LandingScreen"

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val rememberedOnTimeout by rememberUpdatedState(newValue = onTimeout)
        val rememberedAppNameVisibility by rememberSaveable {
            mutableStateOf(false)
        }
        AppName(rememberedAppNameVisibility)
    }
}

@Composable
fun AppName(rememberedAppNameVisibility: Boolean) {
    if(rememberedAppNameVisibility){
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Preview(showBackground = false, showSystemUi = false, device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL, widthDp = 360, heightDp = 640, name = "BasePreview"
)
@Composable
fun PreviewLightSplashScreen(){
    BloomTheme(false) {
        SplashScreen {

        }
    }
}

@Preview(showBackground = false, showSystemUi = false, device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL, widthDp = 360, heightDp = 640, name = "BasePreview"
)
@Composable
fun PreviewDarkSplashScreen(){
    BloomTheme(true) {
        SplashScreen {

        }
    }
}
