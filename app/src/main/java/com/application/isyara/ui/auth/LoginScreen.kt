package com.application.isyara.ui.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var emailOrUsernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val loginState by viewModel.loginState.collectAsState()
    val isLoading = loginState is Result.Loading

    LaunchedEffect(loginState) {
        when (loginState) {
            is Result.Success -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.success_login), Toast.LENGTH_SHORT
                ).show()
                navController.navigate(NavRoute.Dashboard.route) {
                    popUpTo(NavRoute.Login.route) { inclusive = true }
                }
            }

            is Result.Error -> {
                val msg = (loginState as Result.Error).message
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }

            else -> {
            }
        }
    }

    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AppHeaderAuth(
                title = stringResource(R.string.sign_in),
                backgroundDrawable = R.drawable.header_isyara
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CustomInputField(
                    value = identifier,
                    onValueChange = {
                        identifier = it
                        emailOrUsernameError = null
                    },
                    label = stringResource(R.string.email_or_username),
                    placeholder = stringResource(R.string.email_or_username),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = stringResource(R.string.email_or_username)
                        )
                    },
                    isError = emailOrUsernameError != null,
                    errorMessage = emailOrUsernameError,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    isSingleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomInputField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = null
                    },
                    label = context.getString(R.string.password),
                    placeholder = context.getString(R.string.password),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = context.getString(R.string.password)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (isPasswordVisible) context.getString(R.string.hide_password) else context.getString(
                                    R.string.show_password
                                )
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = passwordError != null,
                    errorMessage = passwordError
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoginCheckboxAndForgotPassword(
                    onForgotPasswordClick = {
                        navController.navigate(NavRoute.ForgotPassword.route)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        emailOrUsernameError = null
                        passwordError = null

                        val isIdentifierValid = when {
                            identifier.isBlank() -> {
                                emailOrUsernameError =
                                    context.getString(R.string.email_or_username_must_filled)
                                false
                            }

                            else -> true
                        }

                        val isPasswordValid = when {
                            password.isBlank() -> {
                                passwordError = context.getString(R.string.password_must_filled)
                                false
                            }

                            else -> true
                        }

                        if (isIdentifierValid && isPasswordValid) {
                            viewModel.login(identifier, password)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                    }
                    Text(
                        text = if (isLoading) context.getString(R.string.process) else context.getString(
                            R.string.sign_in
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.dont_have_account))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = context.getString(R.string.register),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(NavRoute.Register.route) {
                                popUpTo(NavRoute.Login.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginCheckboxAndForgotPassword(
    onForgotPasswordClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lupa Kata Sandi?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onForgotPasswordClick() }
            )
        }
    }
}
