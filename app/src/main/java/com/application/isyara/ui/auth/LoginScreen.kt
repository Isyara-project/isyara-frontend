package com.application.isyara.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.auth.loadCredentials

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val (savedEmail, savedPassword) = loadCredentials(context)
        savedEmail?.let { email = it }
        savedPassword?.let { password = it }
        rememberMe = savedEmail != null && savedPassword != null
    }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
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
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                label = "Email",
                placeholder = "Masukkan email Anda",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Email"
                    )
                },
                isError = emailError != null,
                errorMessage = emailError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
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
                rememberMe = rememberMe,
                onRememberMeChange = { rememberMe = it },
                onForgotPasswordClick = {
                    navController.navigate(NavRoute.ForgotPassword.route)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Masuk
            Button(
                onClick = {

                    if (email.isBlank()) emailError = "Email harus diisi!"
                    if (password.isBlank()) passwordError = "Kata sandi harus diisi!"

                    if (emailError == null && passwordError == null) {
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Masuk")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigasi ke Daftar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Belum punya akun?")
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
        // Checkbox "Ingatkan Saya"
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
