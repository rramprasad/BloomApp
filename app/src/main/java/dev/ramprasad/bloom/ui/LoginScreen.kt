package dev.ramprasad.bloom.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.ui.theme.BloomTheme

@Composable
fun LoginScreen() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginTitle()
            Spacer(modifier = Modifier.size(16.dp))
            EmailTextField()
            Spacer(modifier = Modifier.size(8.dp))
            PasswordTextField()
            Spacer(modifier = Modifier.size(8.dp))
            TermsOfUseText()
            Spacer(modifier = Modifier.size(16.dp))
            LoginInButton()
        }
    }
}

@Composable
fun LoginTitle() {
    Text(text = stringResource(id = R.string.login_with_email),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun EmailTextField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = stringResource(id = R.string.email_address),color = MaterialTheme.colors.onPrimary)},
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .border(1.dp, MaterialTheme.colors.surface),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        )

    )
}

@Composable
fun PasswordTextField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = stringResource(id = R.string.password),color = MaterialTheme.colors.onPrimary)},
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .border(1.dp, MaterialTheme.colors.surface),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background
        )
    )
}

@Composable
fun TermsOfUseText() {
    Text(text = stringResource(id = R.string.terms_of_use),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LoginInButton() {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    ) {
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)
@Composable
fun PreviewLightLoginScreen() {
    BloomTheme(false){
        LoginScreen()
    }
}

@Preview(
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    widthDp = 360, heightDp = 640,
    name = "BasePreview"
)
@Composable
fun PreviewDarkLoginScreen() {
    BloomTheme(true) {
        LoginScreen()
    }
}
