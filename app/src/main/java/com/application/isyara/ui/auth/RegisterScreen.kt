package com.application.isyara.ui.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.data.model.RegisterRequest
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.AuthViewModel
import kotlinx.coroutines.delay


@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val result by viewModel.registerState.collectAsState()
    val lastRegistrationAttempt by viewModel.lastRegistrationAttempt.collectAsState()
    val currentTime = System.currentTimeMillis()
    val waitDurationMillis = 10 * 60 * 1000L // 10 menit
    val remainingTimeMillis = if (lastRegistrationAttempt != null) {
        val elapsed = currentTime - lastRegistrationAttempt!!
        if (elapsed < waitDurationMillis) waitDurationMillis - elapsed else 0L
    } else {
        0L
    }
    var remainingTime by remember { mutableLongStateOf(remainingTimeMillis) }

    LaunchedEffect(remainingTimeMillis) {
        if (remainingTimeMillis > 0L) {
            while (remainingTime > 0L) {
                delay(1000L)
                remainingTime -= 1000L
            }
        }
    }

    // Fungsi untuk format waktu
    fun formatTime(millis: Long): String {
        val totalSeconds = millis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format(context.getString(R.string.format_time), minutes, seconds)
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        val regex =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!_%*?&])[A-Za-z\\d@\$!%_*?&]{8,}$".toRegex()
        return regex.matches(password)
    }

    fun isValidUsername(username: String): Boolean {
        val regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$".toRegex()
        return regex.matches(username)
    }

    fun handleRegister() {
        nameError = if (name.isBlank()) context.getString(R.string.name_must_filled) else null
        usernameError = when {
            username.isBlank() -> context.getString(R.string.username_must_filled)
            username.contains(" ") -> context.getString(R.string.username_no_space)
            !isValidUsername(username) -> context.getString(R.string.username_must_complete)
            else -> null
        }
        emailError = when {
            email.isBlank() -> context.getString(R.string.email_must_filled)
            !isValidEmail(email) -> context.getString(R.string.email_not_valid)
            else -> null
        }
        passwordError = when {
            password.isBlank() -> context.getString(R.string.password_must_filled)
            !isValidPassword(password) -> context.getString(R.string.password_must_complete)
            else -> null
        }

        if (nameError == null && usernameError == null && emailError == null && passwordError == null) {
            if (remainingTime <= 0L) {
                isLoading = true
                viewModel.registerUser(
                    RegisterRequest(
                        name,
                        username,
                        email,
                        password
                    )
                )
            }
        }
    }

    LaunchedEffect(result) {
        when (result) {
            is Result.Success -> {
                isLoading = false
                val token = (result as Result.Success).data.token
                Toast.makeText(
                    context,
                    context.getString(R.string.register_success), Toast.LENGTH_SHORT
                ).show()
                navController.navigate("${NavRoute.Otp.route}/$token") {
                    popUpTo(NavRoute.Register.route) { inclusive = true }
                }
            }

            is Result.Error -> {
                isLoading = false
                val errorMsg = (result as Result.Error).message
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppHeaderAuth(
                title = context.getString(R.string.register),
                backgroundDrawable = R.drawable.header_isyara
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Input Nama
                CustomInputField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = null
                    },
                    label = stringResource(R.string.name),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = stringResource(R.string.name)
                        )
                    },
                    placeholder = stringResource(R.string.name),
                    isError = nameError != null,
                    errorMessage = nameError
                )

                // Input Username
                CustomInputField(
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError = null
                    },
                    label = stringResource(R.string.username),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_username),
                            contentDescription = stringResource(R.string.username)
                        )
                    },
                    placeholder = stringResource(R.string.username),
                    isError = usernameError != null,
                    errorMessage = usernameError
                )

                // Input Email
                CustomInputField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = null
                    },
                    label = stringResource(R.string.email),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = stringResource(R.string.email)
                        )
                    },
                    placeholder = stringResource(R.string.email),
                    isError = emailError != null,
                    errorMessage = emailError
                )

                // Input Kata Sandi
                CustomInputField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = null
                    },
                    label = stringResource(R.string.password),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = stringResource(R.string.password)
                        )
                    },
                    placeholder = stringResource(R.string.password),
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible }
                        ) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (isPasswordVisible) stringResource(R.string.hide_password) else stringResource(
                                    R.string.show_password
                                )
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { handleRegister() },
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
                        text = if (isLoading) stringResource(R.string.process) else stringResource(
                            R.string.register
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (remainingTime > 0L) {
                    Text(
                        text = stringResource(R.string.please_wait, formatTime(remainingTime)),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.have_account))
                    Text(
                        text = context.getString(R.string.login),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(NavRoute.Login.route) {
                                popUpTo(NavRoute.Register.route) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}
