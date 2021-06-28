/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */

package dev.ramprasad.bloom.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.theme.BloomTheme
import dev.ramprasad.bloom.feature.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun LoginScreen(loginViewModel:LoginViewModel = hiltViewModel(),onLoginSuccess: () -> Unit) {
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
            val currentContext = LocalContext.current
            LoginTitle()
            Spacer(modifier = Modifier.size(16.dp))
            val emailState = rememberSaveable {
                mutableStateOf("")
            }
            val emailErrorState = rememberSaveable {
                mutableStateOf(currentContext.getString(R.string.email_address_label))
            }
            EmailTextField(emailState,emailErrorState)
            Spacer(modifier = Modifier.size(8.dp))

            val passwordState = rememberSaveable {
                mutableStateOf("")
            }
            val passwordErrorState = rememberSaveable {
                mutableStateOf(currentContext.getString(R.string.password_label))
            }
            PasswordTextField(passwordState,passwordErrorState)
            Spacer(modifier = Modifier.size(8.dp))
            TermsOfUseText()
            Spacer(modifier = Modifier.size(16.dp))
            LoginInButton{
                if(emailState.value.isEmpty()){
                    emailErrorState.value = currentContext.getString(R.string.required_field_empty_message)
                }

                if(passwordState.value.isEmpty()){
                    passwordErrorState.value = currentContext.getString(R.string.required_field_empty_message)
                }

                if(emailState.value.isNotEmpty() && passwordState.value.isNotEmpty()) {
                    loginViewModel.onLogin(emailState.value, passwordState.value)
                }
            }

            val loginSuccess = loginViewModel.loginResultState.collectAsState().value
            if(loginSuccess){
                //Toast.makeText(currentContext, "Login SUCCESS", Toast.LENGTH_SHORT).show()
                onLoginSuccess()
            }
            else{
                Toast.makeText(currentContext, "Login FAILED", Toast.LENGTH_SHORT).show()
            }
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
fun EmailTextField(emailState: MutableState<String>,emailErrorState : MutableState<String>  ) {
    val currentContext = LocalContext.current
    /*var errorOccured by rememberSaveable {
        mutableStateOf(false)
    }
    var errorMessage by rememberSaveable {
        mutableStateOf("")
    }*/

    OutlinedTextField(
        value = emailState.value,
        onValueChange = {
            emailState.value = it
        },
        label = {
            Text(emailErrorState.value)
        },
        isError = emailErrorState.value != currentContext.getString(R.string.email_address_label),
        placeholder = { Text(text = stringResource(id = R.string.email_address),color = MaterialTheme.colors.onPrimary)},
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            textColor = MaterialTheme.colors.onPrimary,
            focusedIndicatorColor = MaterialTheme.colors.onPrimary,
            focusedLabelColor = MaterialTheme.colors.onPrimary,
            cursorColor = MaterialTheme.colors.onPrimary
        )
    )
}

@Composable
fun PasswordTextField(passwordState: MutableState<String>,passwordErrorState: MutableState<String>) {
    val currentContext = LocalContext.current
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
                passwordState.value = it
        },
        label = {
            Text(passwordErrorState.value)
        },
        placeholder = { Text(text = stringResource(id = R.string.password),color = MaterialTheme.colors.onPrimary)},
        isError = passwordErrorState.value != currentContext.getString(R.string.password_label),
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            textColor = MaterialTheme.colors.onPrimary,
            focusedIndicatorColor = MaterialTheme.colors.onPrimary,
            focusedLabelColor = MaterialTheme.colors.onPrimary,
            cursorColor = MaterialTheme.colors.onPrimary
        )
    )
}

@Composable
fun TermsOfUseText() {
    val annotatedString1 = buildAnnotatedString {
        append(stringResource(id = R.string.terms_of_use_1))
        addStringAnnotation(
            tag = "TERMS_OF_USE",
            annotation = "https://developer.android.com",
            start = 35,
            end = 46
        )
        addStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
            ),
            start = 36,
            end = 48
        )

        addStyle(
            SpanStyle(
                color = MaterialTheme.colors.onPrimary
            ),
            start = 0,
            end = 48
        )
    }

    val annotatedString2 = buildAnnotatedString {
        append(stringResource(id = R.string.terms_of_use_2))
        addStringAnnotation(
            tag = "PRIVACY_POLICY",
            annotation = "https://developer.android.com",
            start = 19,
            end = 33
        )
        addStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline
            ),
            start = 19,
            end = 33
        )

        addStyle(
            SpanStyle(
                color = MaterialTheme.colors.onPrimary
            ),
            start = 0,
            end = 33
        )
    }

    val annotatedString = buildAnnotatedString {
        withStyle(ParagraphStyle(
            textAlign = TextAlign.Center
        )){
            append(annotatedString1)
            append(" ")
            append(annotatedString2)
        }
    }

    val currentContext = LocalContext.current
    ClickableText(text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        style = MaterialTheme.typography.body2,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = "TERMS_OF_USE",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                launchInBrowser(it, currentContext)
            }

            annotatedString.getStringAnnotations(
                tag = "PRIVACY_POLICY",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                launchInBrowser(it, currentContext)
            }
        }
    )
}

private fun launchInBrowser(
    it: AnnotatedString.Range<String>,
    currentContext: Context
) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(it.item)
    currentContext.startActivity(intent)
}

@Composable
fun LoginInButton(onLoginButtonClicked : () -> Unit) {
    //val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    //val onLoginClicked by rememberUpdatedState(newValue = onLoginButtonClicked)
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            //onLoginClicked()
            onLoginButtonClicked()
            //loginViewModel.onLogin()
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
        LoginScreen(){

        }
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
        LoginScreen(){

        }
    }
}
