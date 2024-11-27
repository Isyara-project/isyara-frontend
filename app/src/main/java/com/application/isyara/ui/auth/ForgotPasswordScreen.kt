package com.application.isyara.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var isOtpFieldVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var otpError by remember { mutableStateOf<String?>(null) }
    val simulatedOtp = "123456" // Simulasi OTP dari server

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        AppHeaderAuth(
            title = "Lupa Kata Sandi",
            backgroundDrawable = R.drawable.header_isyara
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Konten
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!isOtpFieldVisible) {
                // Input Email
                CustomInputField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = null
                    },
                    label = "Email",
                    placeholder = "Masukkan email Anda",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    isError = emailError != null,
                    errorMessage = emailError
                )

                // Tombol Kirim
                Button(
                    onClick = {
                        if (email.isBlank()) {
                            emailError = "Email harus diisi!"
                        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "Format email tidak valid!"
                        } else if (email != "user@example.com") { // Contoh validasi
                            emailError = "Email Anda tidak terdaftar!"
                        } else {
                            emailError = null
                            isOtpFieldVisible = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Kirim")
                }
            } else {
                // Input OTP
                Text(
                    text = "Masukkan kode OTP yang telah dikirim ke email Anda",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                CustomInputField(
                    value = otp,
                    onValueChange = {
                        otp = it
                        otpError = null
                    },
                    label = "Kode OTP",
                    placeholder = "Masukkan kode OTP",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    isError = otpError != null,
                    errorMessage = otpError
                )

                // Tombol Verifikasi OTP
                Button(
                    onClick = {
                        if (otp.isBlank()) {
                            otpError = "Kode OTP harus diisi!"
                        } else if (otp != simulatedOtp) { // Validasi OTP
                            otpError = "Kode OTP tidak valid!"
                        } else {
                            otpError = null
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Verifikasi")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        navController = NavHostController(LocalContext.current)
    )
}
