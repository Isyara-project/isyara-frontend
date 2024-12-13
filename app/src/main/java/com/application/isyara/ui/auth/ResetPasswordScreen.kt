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
                otpError = "Kode OTP harus diisi!"
            }
            otp.length != 6 || !otp.all { it.isDigit() } -> {
                otpError = "Kode OTP harus terdiri dari 6 digit angka!"
            }
            else -> {
                otpError = null
                isOtpVerified = true
                Toast.makeText(context, "OTP berhasil diverifikasi!", Toast.LENGTH_SHORT).show()
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
            passwordError = "Password tidak boleh kosong!"
            isValid = false
        } else if (password.length < 8 || password.length > 16) {
            passwordError = "Password harus memiliki panjang 8-16 karakter!"
            isValid = false
        } else if (!password.matches(Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#]).+$"))) {
            passwordError = "Password harus mengandung 1 huruf besar, 1 angka, dan 1 karakter spesial!"
            isValid = false
        }

        if (confirmPassword.isBlank()) {
            confirmPasswordError = "Konfirmasi password tidak boleh kosong!"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordError = "Kata sandi dan konfirmasi kata sandi tidak cocok!"
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
                Toast.makeText(context, "Password berhasil diatur ulang!", Toast.LENGTH_SHORT).show()
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
            title = "Atur Ulang Kata Sandi",
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
                    label = "Kode OTP",
                    placeholder = "Masukkan kode OTP",
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
                    Text(text = if (isLoading) "Memproses..." else "Verifikasi OTP")
                }
            } else {

                CustomInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password Baru",
                    placeholder = "Masukkan password baru",
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                CustomInputField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Konfirmasi Password",
                    placeholder = "Masukkan konfirmasi password",
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
                    Text(text = if (isLoading) "Memproses..." else "Atur Ulang Kata Sandi")
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
