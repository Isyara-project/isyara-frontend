package com.application.isyara.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.isyara.R
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.viewmodel.auth.AuthViewModel
import com.application.isyara.data.model.ForgotPasswordRequest
import com.application.isyara.utils.auth.Result

@Composable
fun ForgotPasswordScreen(navController: NavHostController, authViewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var isOtpFieldVisible by remember { mutableStateOf(false) }
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }

    // State untuk menampilkan loading atau notifikasi
    val loadingState = authViewModel.loadingState.collectAsState().value
    val forgotPasswordState = authViewModel.forgotPasswordState.collectAsState().value

    val context = LocalContext.current

    // Handler untuk kirim email
    fun handleForgotPassword() {
        if (email.isBlank()) {
            emailError = "Email harus diisi!"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Format email tidak valid!"
        } else {
            emailError = null
            val forgotPasswordRequest = ForgotPasswordRequest(email)
            authViewModel.forgotPassword(forgotPasswordRequest) // Panggil fungsi forgotPassword dari ViewModel
        }
    }

    // Observer untuk state forgotPassword
    LaunchedEffect(forgotPasswordState) {
        when (forgotPasswordState) {
            is Result.Success -> {
                // Tampilkan pesan sukses jika berhasil
                Toast.makeText(context, "Link reset kata sandi telah dikirim ke email", Toast.LENGTH_SHORT).show()
                isOtpFieldVisible = true
            }
            is Result.Error -> {
                // Tampilkan error jika ada masalah
                Toast.makeText(context, forgotPasswordState.message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
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
                    onClick = { handleForgotPassword() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Kirim")
                }

                // Menampilkan progress bar saat loading
                if (loadingState) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
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
                        } else {
                            otpError = null
                            // Verifikasi OTP atau reset password
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
    ForgotPasswordScreen(navController = NavHostController(LocalContext.current))
}
