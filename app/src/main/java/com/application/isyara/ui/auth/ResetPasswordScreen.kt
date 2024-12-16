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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.ResetPasswordViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    token: String,
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
) {
    // State variables
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isOtpVerified by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val resetPasswordState by resetPasswordViewModel.resetPasswordState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    /**
     * Handle OTP Verification
     */
    fun handleOtpVerification() {
        when {
            otp.isBlank() -> {
                otpError = context.getString(R.string.otp_must_filled)
            }
            otp.length != 6 || !otp.all { it.isDigit() } -> {
                otpError = context.getString(R.string.otp_must_6)
            }
            else -> {
                otpError = null
                isOtpVerified = true
                Toast.makeText(context, context.getString(R.string.otp_successfully_verified), Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Handle Reset Password Logic
     */
    fun handleResetPassword() {
        passwordError = null
        confirmPasswordError = null
        errorMessage = null

        var isValid = true

        if (password.isBlank()) {
            passwordError = context.getString(R.string.password_must_filled)
            isValid = false
        } else if (password.length < 8 || password.length > 16) {
            passwordError = context.getString(R.string.password_8_16)
            isValid = false
        } else if (!password.matches(Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#]).+$"))) {
            passwordError = context.getString(R.string.password_must_complete)
            isValid = false
        }

        if (confirmPassword.isBlank()) {
            confirmPasswordError = context.getString(R.string.confirm_password_must_filled)
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordError = context.getString(R.string.password_not_same)
            isValid = false
        }

        if (isValid) {
            resetPasswordViewModel.resetPassword(token, password)
        }
    }

    /**
     * Observe Reset Password State
     */
    LaunchedEffect(resetPasswordState) {
        when (resetPasswordState) {
            is Result.Loading -> {
                isLoading = true
            }

            is Result.Success -> {
                isLoading = false
                Toast.makeText(context,
                    context.getString(R.string.password_succes), Toast.LENGTH_SHORT).show()
                navController.navigate(NavRoute.Login.route) {
                    popUpTo(NavRoute.ResetPassword.route) { inclusive = true }
                }
                resetPasswordViewModel.resetState()
            }

            is Result.Error -> {
                isLoading = false
                errorMessage = (resetPasswordState as Result.Error).message
                resetPasswordViewModel.resetState()
            }

            else -> {
                isLoading = false
            }
        }
    }

    /**
     * Observe Error Message
     */
    LaunchedEffect(errorMessage) {
        if (!isLoading && errorMessage != null) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            errorMessage = null
        }
    }

    /**
     * Layout UI
     */
    Column(modifier = Modifier.fillMaxSize()) {
        // Header Section
        AppHeaderAuth(
            title = stringResource(R.string.reset_password),
            backgroundDrawable = R.drawable.header_isyara
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!isOtpVerified) {

                CustomInputField(
                    value = otp,
                    onValueChange = { otp = it },
                    label = context.getString(R.string.input_otp),
                    placeholder = context.getString(R.string.input_otp),
                    isError = otpError != null,
                    errorMessage = otpError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    onClick = { handleOtpVerification() },
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
                    Text(text = if (isLoading) context.getString(R.string.process) else context.getString(R.string.verify_otp))
                }
            } else {

                CustomInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = context.getString(R.string.new_password),
                    placeholder = context.getString(R.string.new_password),
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                CustomInputField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = context.getString(R.string.confirm_password),
                    placeholder = context.getString(R.string.password_must_filled),
                    isError = confirmPasswordError != null,
                    errorMessage = confirmPasswordError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Button(
                    onClick = { handleResetPassword() },
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
                    Text(text = if (isLoading) context.getString(R.string.process) else context.getString(R.string.reset_password))
                }

                errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
