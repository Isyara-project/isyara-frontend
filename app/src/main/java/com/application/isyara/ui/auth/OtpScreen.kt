package com.application.isyara.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.data.model.OtpRequest
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.AuthViewModel
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun OtpScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel(),
    token: String
) {
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }
    var resendTimer by remember { mutableIntStateOf(120) }
    var isResendEnabled by remember { mutableStateOf(false) }

    // Fungsi untuk format timer
    val minutes = resendTimer / 60
    val seconds = resendTimer % 60
    val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

    // Timer countdown, setiap detik berkurang
    LaunchedEffect(resendTimer) {
        if (resendTimer > 0) {
            delay(1000)
            resendTimer -= 1
        } else {
            isResendEnabled = true
        }
    }

    // Observasi perubahan state dari AuthViewModel
    val otpState by viewModel.otpState.collectAsState()
    val resendOtpState by viewModel.resendOtpState.collectAsState()

    // Cek hasil dari OTP verifikasi dan resend
    LaunchedEffect(otpState) {
        when (otpState) {
            is Result.Success -> {
                navController.navigate(NavRoute.Onboarding.route)
            }

            is Result.Error -> {
                otpError =
                    (otpState as Result.Error).message
            }

            else -> otpError = null
        }
    }

    LaunchedEffect(resendOtpState) {
        otpError = when (resendOtpState) {
            is Result.Success -> null
            is Result.Error -> (resendOtpState as Result.Error).message
            else -> null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppHeaderAuth(
            title = "Verifikasi OTP",
            backgroundDrawable = R.drawable.header_isyara
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Input OTP
            CustomInputField(
                value = otp,
                onValueChange = { it ->
                    if (it.length <= 6) otp = it.filter { it.isDigit() }
                    otpError = null // Reset error saat input berubah
                },
                label = "Masukkan Kode OTP",
                placeholder = "Masukkan kode OTP",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_otp),
                        contentDescription = "OTP"
                    )
                },
                isError = otpError != null,
                errorMessage = otpError,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Verifikasi OTP
            Button(
                onClick = {
                    otpError = when {
                        otp.isBlank() -> "Kode OTP harus diisi!" // Pastikan OTP tidak kosong
                        otp.length != 6 -> "Kode OTP harus 6 digit!" // Pastikan panjang OTP 6 digit
                        else -> null
                    }

                    if (otpError == null) {
                        // Kirim OTP untuk diverifikasi
                        val otpRequest = OtpRequest(otp)
                        viewModel.verifyOtp(otpRequest, token)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Verifikasi OTP")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Kirim Ulang OTP
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Tidak menerima OTP? ")
                if (isResendEnabled) {
                    Text(
                        text = "Kirim ulang",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            viewModel.resendOtp(token)
                            resendTimer = 120 // Reset timer
                            isResendEnabled = false // Nonaktifkan kirim ulang saat timer berjalan
                            otp = "" // Kosongkan OTP input setelah kirim ulang
                            otpError = null // Reset error setelah kirim ulang
                        }
                    )
                } else {
                    Text(text = "Tunggu $formattedTime")
                }
            }
        }
    }
}
