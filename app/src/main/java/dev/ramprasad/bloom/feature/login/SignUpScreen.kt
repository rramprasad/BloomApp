/*
 * Created by Ramprasad Ranganathan on 29/06/21, 2:44 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 29/06/21, 2:44 PM
 */
package dev.ramprasad.bloom.feature.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ramprasad.bloom.R
import dev.ramprasad.bloom.theme.BloomTheme

@Composable
fun SignUpScreen(loginViewModel: LoginViewModel = hiltViewModel(), onSignUpSuccess: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val currentContext = LocalContext.current

            // Title
            SignUpTitle()
            Spacer(modifier = Modifier.size(16.dp))

            // Email
            val emailState = rememberSaveable {
                mutableStateOf("")
            }
            val emailErrorState = rememberSaveable {
                mutableStateOf(currentContext.getString(R.string.email_address_label))
            }
            SignUpEmailTextField(emailState, emailErrorState)
            Spacer(modifier = Modifier.size(8.dp))

            // Password
            val passwordState = rememberSaveable {
                mutableStateOf("")
            }
            val passwordErrorState = rememberSaveable {
                mutableStateOf(currentContext.getString(R.string.password_label))
            }
            SignUpPasswordTextField(passwordState, passwordErrorState)
            Spacer(modifier = Modifier.size(16.dp))

            // Confirm Password
            val confirmPasswordState = rememberSaveable {
                mutableStateOf("")
            }
            val confirmPasswordErrorState = rememberSaveable {
                mutableStateOf(currentContext.getString(R.string.confirm_password_label))
            }
            SignUpConfirmPasswordTextField(confirmPasswordState, confirmPasswordErrorState)
            Spacer(modifier = Modifier.size(16.dp))

            // Sign Up Button
            SignUpButton {
                if (emailState.value.isEmpty()) {
                    emailErrorState.value =
                        currentContext.getString(R.string.required_field_empty_message)
                }

                if (passwordState.value.isEmpty()) {
                    passwordErrorState.value =
                        currentContext.getString(R.string.required_field_empty_message)
                }

                if (confirmPasswordState.value.isEmpty()) {
                    confirmPasswordErrorState.value =
                        currentContext.getString(R.string.required_field_empty_message)
                }

                if (emailState.value.isNotEmpty() && passwordState.value.isNotEmpty() && confirmPasswordState.value.isNotEmpty()) {
                    if (passwordState.value == confirmPasswordState.value) {
                        loginViewModel.onSignUp(emailState.value, passwordState.value)
                    } else {
                        confirmPasswordErrorState.value =
                            currentContext.getString(R.string.password_not_matched)
                    }
                }
            }

            val signUpSuccess = loginViewModel.signUpResultState.collectAsState().value
            if (signUpSuccess) {
                onSignUpSuccess()
            } else {
                // DO NOTHING
            }
        }
    }
}

@Composable
fun SignUpTitle() {
    Text(
        text = stringResource(id = R.string.sign_up_with_email),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SignUpEmailTextField(emailState: MutableState<String>, emailErrorState: MutableState<String>) {
    val currentContext = LocalContext.current
    OutlinedTextField(
        value = emailState.value,
        onValueChange = {
            emailState.value = it
        },
        label = {
            Text(emailErrorState.value)
        },
        isError = emailErrorState.value != currentContext.getString(R.string.email_address_label),
        placeholder = {
            Text(
                text = stringResource(id = R.string.email_address),
                color = MaterialTheme.colors.onPrimary
            )
        },
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
fun SignUpPasswordTextField(
    passwordState: MutableState<String>,
    passwordErrorState: MutableState<String>
) {
    val currentContext = LocalContext.current
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = {
            Text(passwordErrorState.value)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.password),
                color = MaterialTheme.colors.onPrimary
            )
        },
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
fun SignUpConfirmPasswordTextField(
    confirmPasswordState: MutableState<String>,
    confirmPasswordErrorState: MutableState<String>
) {
    val currentContext = LocalContext.current
    OutlinedTextField(
        value = confirmPasswordState.value,
        onValueChange = {
            confirmPasswordState.value = it
        },
        label = {
            Text(confirmPasswordErrorState.value)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.confirm_password),
                color = MaterialTheme.colors.onPrimary
            )
        },
        isError = confirmPasswordErrorState.value != currentContext.getString(R.string.confirm_password_label),
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
fun SignUpButton(onSignUpButtonClicked: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onSignUpButtonClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    ) {
        Text(
            text = stringResource(R.string.signup),
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
fun PreviewDarkLoginScreen() {
    BloomTheme(true) {
        SignUpScreen {

        }
    }
}