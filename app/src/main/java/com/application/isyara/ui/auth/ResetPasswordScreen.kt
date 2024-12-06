package com.application.isyara.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.auth.Result
import com.application.isyara.viewmodel.auth.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.isyara.data.model.ResetPasswordRequest
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
    token: String
) {
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }
    var isOtpVerified by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val resetPasswordState by authViewModel.resetPasswordState.collectAsState()
    val isLoading by authViewModel.loadingState.collectAsState()

    val context = LocalContext.current

    // Handle OTP verification
    fun handleOtpVerification() {
        if (otp.isBlank()) {
            otpError = "Kode OTP harus diisi!"
        } else {
            otpError = null
            isOtpVerified = true
        }
    }

    // Handle Reset Password Logic
    fun handleResetPassword() {
        // Reset error message
        errorMessage = null

        // Validation checks
        when {
            password.isBlank() || confirmPassword.isBlank() -> {
                errorMessage = "Kata sandi tidak boleh kosong!"
            }
            password != confirmPassword -> {
                errorMessage = "Kata sandi dan konfirmasi kata sandi tidak cocok!"
            }
            password.length < 8 || password.length > 16 -> {
                errorMessage = "Kata sandi harus memiliki panjang 8-16 karakter!"
            }
            !password.matches(Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]+$")) -> {
                errorMessage = "Kata sandi harus mengandung 1 huruf besar, 1 angka, dan 1 karakter spesial!"
            }
            token.isBlank() -> {
                errorMessage = "Token tidak valid!"
            }
            else -> {
                // Proceed with password reset logic
                authViewModel.resetPassword(resetPasswordRequest = ResetPasswordRequest(password), token = token)
            }
        }
    }

    // Effect to show Toast on success or error state
    LaunchedEffect(resetPasswordState) {
        when (resetPasswordState) {
            is Result.Success -> {
                Toast.makeText(context, "Password berhasil diatur ulang!", Toast.LENGTH_SHORT).show()

                // Navigate to LoginScreen after resetting password
                navController.navigate(NavRoute.Login.route) {
                    // Clear the back stack to ensure user can't go back to reset screen
                    popUpTo(NavRoute.ResetPassword.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
            is Result.Error -> {
                Toast.makeText(
                    context,
                    (resetPasswordState as Result.Error).message ?: "Gagal mereset kata sandi",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header Section
        AppHeaderAuth(
            title = "Atur Ulang Kata Sandi",
            backgroundDrawable = R.drawable.header_isyara
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Reset Password Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!isOtpVerified) {
                // Form Input for OTP
                Text(
                    text = "Masukkan kode OTP yang telah dikirim ke email Anda",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                CustomInputField(
                    value = otp,
                    onValueChange = { otp = it },
                    label = "Kode OTP",
                    placeholder = "Masukkan kode OTP",
                    isError = otpError != null,
                    errorMessage = otpError
                )

                // OTP Verification Button
                Button(
                    onClick = { handleOtpVerification() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Verifikasi OTP")
                }

                // Show loading progress while waiting for verification
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            } else {
                // Form Input for Password if OTP is verified
                CustomInputField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password Baru",
                    placeholder = "Masukkan password baru",
                    isError = errorMessage != null && password.isBlank(),
                    errorMessage = if (password.isBlank()) errorMessage else null
                )

                CustomInputField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Konfirmasi Password",
                    placeholder = "Masukkan konfirmasi password",
                    isError = errorMessage != null && confirmPassword.isBlank(),
                    errorMessage = if (confirmPassword.isBlank()) errorMessage else null
                )

                // Reset Password Button
                Button(
                    onClick = { handleResetPassword() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading // Disable button while loading
                ) {
                    Text(text = if (isLoading) "Memproses..." else "Atur Ulang Kata Sandi")
                }

                // Display error message if any
                errorMessage?.let {
                    Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }

                // Show loading progress indicator if still loading
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }
}
