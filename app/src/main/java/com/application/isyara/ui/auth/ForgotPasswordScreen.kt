package com.application.isyara.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.ForgotPasswordViewModel
import com.application.isyara.viewmodel.auth.ResetPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var isOtpFieldVisible by remember { mutableStateOf(false) }
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }
    val forgotPasswordState = forgotPasswordViewModel.forgotPasswordState.collectAsState().value
    var token by remember { mutableStateOf<String?>(null) }

    fun handleForgotPassword() {
        if (email.isBlank()) {
            emailError = "Email harus diisi!"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Format email tidak valid!"
        } else {
            emailError = null
            forgotPasswordViewModel.forgotPassword(email)
        }
    }

    // Observer untuk state forgotPassword
    LaunchedEffect(forgotPasswordState) {
        when (forgotPasswordState) {
            is Result.Success -> {
                // Asumsikan token dikirim dalam response API
                token = forgotPasswordState.data.token
                Toast.makeText(
                    context,
                    "Link reset kata sandi telah dikirim ke email",
                    Toast.LENGTH_SHORT
                ).show()

                // Navigasi ke ResetPasswordScreen dengan token
                token?.let {
                    navController.navigate("${NavRoute.ResetPassword.route}/$it")
                }
            }

            is Result.Error -> {
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

                // Menampilkan progress bar hanya ketika sedang loading
                if (forgotPasswordState is Result.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            } else {
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

                Button(
                    onClick = {
                        if (otp.isBlank()) {
                            otpError = "Kode OTP harus diisi!"
                        } else {
                            otpError = null
                            token?.let {
                                navController.navigate("resetPasswordScreen?token=$it")
                            }
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
