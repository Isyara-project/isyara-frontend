package com.application.isyara.ui.auth

import android.util.Patterns
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
import androidx.compose.material.rememberScaffoldState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.application.isyara.viewmodel.auth.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val scaffoldState = rememberScaffoldState()

    val loginState by viewModel.loginState.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is Result.Success -> {
                navController.navigate(NavRoute.Dashboard.route) {
                    popUpTo(NavRoute.Login.route) { inclusive = true }
                }
            }

            is Result.Error -> {
                scaffoldState.snackbarHostState.showSnackbar("Login failed: ${errorState ?: "Unknown error"}")
            }

            else -> {}
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            AppHeaderAuth(
                title = "Masuk",
                backgroundDrawable = R.drawable.header_isyara
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Konten Login
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Input Email
                CustomInputField(
                    value = identifier,
                    onValueChange = {
                        identifier = it
                        emailError = null
                    },
                    label = "Email",
                    placeholder = "Masukkan Email / Username Anda",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Email"
                        )
                    },
                    isError = emailError != null,
                    errorMessage = emailError,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    isSingleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Kata Sandi
                CustomInputField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = null
                    },
                    label = "Kata Sandi",
                    placeholder = "Masukkan kata sandi Anda",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = "Kata Sandi"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = passwordError != null,
                    errorMessage = passwordError
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Checkbox & Lupa Password
                LoginCheckboxAndForgotPassword(
                    rememberMe = false,
                    onRememberMeChange = { },
                    onForgotPasswordClick = {
                        navController.navigate(NavRoute.ForgotPassword.route)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Masuk
                Button(
                    onClick = {
                        emailError = null
                        passwordError = null

                        // Validasi Email dan Password
                        if (identifier.isBlank()) {
                            emailError = "Email harus diisi!"
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(identifier).matches()) {
                            emailError = "Format email tidak valid!"
                        }

                        if (password.isBlank()) {
                            passwordError = "Kata sandi harus diisi!"
                        }

                        // Jika tidak ada error, lakukan login
                        if (emailError == null && passwordError == null) {
                            viewModel.login(identifier, password)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !loadingState && emailError == null && passwordError == null
                ) {
                    if (loadingState) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Masuk")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Menampilkan pesan error jika ada
                errorState?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Belum punya akun? ")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Daftar",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(NavRoute.Register.route)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun LoginCheckboxAndForgotPassword(
    rememberMe: Boolean,
    onRememberMeChange: (Boolean) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = onRememberMeChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Ingatkan Saya", style = MaterialTheme.typography.bodyMedium)
        }

        // Lupa Kata Sandi
        Text(
            text = "Lupa Kata Sandi?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onForgotPasswordClick() }
        )
    }
}
