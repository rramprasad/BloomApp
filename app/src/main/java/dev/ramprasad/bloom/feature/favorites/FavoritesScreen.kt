/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.theme.BloomTheme

@Composable
fun FavoritesScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.favorites),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .paddingFromBaseline(32.dp, 0.dp)
                    .padding(16.dp, 16.dp)
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)
@Composable
fun PreviewLightFavoritesScreen() {
    BloomTheme(false){
        FavoritesScreen()
    }
}


@ExperimentalMaterialApi
@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)
@Composable
fun PreviewDarkFavoritesScreen() {
    BloomTheme(true) {
        FavoritesScreen()
    }
}