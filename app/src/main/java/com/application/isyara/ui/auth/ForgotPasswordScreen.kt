package com.application.isyara.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.data.model.ForgotPasswordResponse
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var localEmailError by remember { mutableStateOf<String?>(null) }
    val forgotPasswordState by forgotPasswordViewModel.forgotPasswordState.collectAsState()
    val serverEmailError by forgotPasswordViewModel.emailError.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    fun handleForgotPassword() {
        localEmailError = null

        when {
            email.isBlank() -> {
                localEmailError = "Email harus diisi!"
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                localEmailError = "Format email tidak valid!"
            }

            serverEmailError != null -> {
                Toast.makeText(context, serverEmailError, Toast.LENGTH_SHORT).show()
            }

            else -> {
                forgotPasswordViewModel.forgotPassword(email)
            }
        }
    }

    LaunchedEffect(forgotPasswordState) {
        when (forgotPasswordState) {
            is Result.Loading -> {
                isLoading = true
            }

            is Result.Success -> {
                isLoading = false
                val response = (forgotPasswordState as Result.Success<ForgotPasswordResponse>).data
                val token = response.token
                Toast.makeText(
                    context,
                    context.getString(R.string.link_send_reset),
                    Toast.LENGTH_SHORT
                ).show()

                token?.let { tokenValue ->
                    navController.navigate("${NavRoute.ResetPassword.route}/$tokenValue") {
                        popUpTo(NavRoute.ForgotPassword.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
                forgotPasswordViewModel.processForgotPasswordResult()
                forgotPasswordViewModel.resetState()
            }

            is Result.Error -> {
                isLoading = false
                val errorMessage = (forgotPasswordState as Result.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                forgotPasswordViewModel.resetState()
            }

            else -> {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        AppHeaderAuth(
            title = stringResource(R.string.forgot_password),
            backgroundDrawable = R.drawable.header_isyara
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Konten
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomInputField(
                value = email,
                onValueChange = {
                    email = it
                    localEmailError = null
                },
                label = context.getString(R.string.email),
                placeholder = context.getString(R.string.email),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                isError = localEmailError != null || serverEmailError != null,
                errorMessage = localEmailError ?: serverEmailError
            )

            Button(
                onClick = { handleForgotPassword() },
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
                Text(text = if (isLoading) context.getString(R.string.process) else context.getString(R.string.send))
            }
        }
    }
}