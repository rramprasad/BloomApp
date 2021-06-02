/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.ui.theme.BloomTheme


@Composable
fun WelcomeScreen(onCreateAccountClicked : () -> Unit, onLoginClicked : () -> Unit){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primary)
    ){

        WelcomeBackGround()

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            WelcomeIllos()
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AppTitle()
                Spacer(modifier = Modifier.size(40.dp))
                CreateAccountButton(onCreateAccountClicked)
                Spacer(modifier = Modifier
                    .size(8.dp)
                    .fillMaxWidth())
                LoginLink(onLoginClicked)
            }
        }
    }
}

@Composable
private fun LoginLink(onLoginClicked: () -> Unit) {
    val rememberLoginClicked by rememberUpdatedState(newValue = onLoginClicked)

    Text(
        text = AnnotatedString(stringResource(R.string.login)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                rememberLoginClicked()
            }
            .padding(0.dp, 16.dp),
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CreateAccountButton(onCreateAccountClicked: () -> Unit) {
    val createAccountClicked by rememberUpdatedState(newValue = onCreateAccountClicked)
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            createAccountClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    ) {
        Text(
            text = stringResource(R.string.create_account),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun AppTitle() {
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.bloom),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.beautiful_home_garden_solutions),
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun WelcomeIllos() {
    Image(
            painter = if(MaterialTheme.colors.isLight) {
                painterResource(id = R.drawable.ic_light_welcome_illos)
            } else{
                painterResource(id = R.drawable.ic_dark_welcome_illos)
            },
            contentDescription = null,
            modifier = Modifier
                .padding(88.dp, 72.dp, 0.dp, 48.dp),
                //.offset(32.dp, 0.dp),
            contentScale = ContentScale.Crop
        )
}

@Composable
fun WelcomeBackGround() {
    if(MaterialTheme.colors.isLight) {
        Image(
            painterResource(id = R.drawable.ic_light_welcome_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
    else{
        Image(
            painterResource(id = R.drawable.ic_dark_welcome_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    //widthDp = 360, heightDp = 640,
    widthDp = 430, heightDp = 906,
    name = "BasePreview",
    showBackground = false
)
@Composable
fun PreviewLightWelcome(){
    BloomTheme(false) {
        WelcomeScreen({}){}
    }
}

@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)
@Composable
fun PreviewDarkWelcome(){
    BloomTheme(true) {
        WelcomeScreen({}){}
    }
}
