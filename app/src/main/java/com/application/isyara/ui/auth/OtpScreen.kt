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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale

@Composable
fun OtpScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel(),
    token: String
) {
    var otp by remember { mutableStateOf("") }
    var otpError by remember { mutableStateOf<String?>(null) }
    var resendTimer by remember { mutableStateOf(120) }
    var isResendEnabled by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val otpState by viewModel.otpState.collectAsState()
    val resendOtpState by viewModel.resendOtpState.collectAsState()
    val minutes = resendTimer / 60
    val seconds = resendTimer % 60
    val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(resendTimer) {
        if (!isResendEnabled && resendTimer > 0) {
            delay(1000L)
            resendTimer -= 1
        } else if (resendTimer <= 0 && !isResendEnabled) {
            isResendEnabled = true
            otpError = "Kode OTP anda kadaluarsa, silahkan kirim ulang OTP."
        }
    }

    LaunchedEffect(otpState) {
        when (otpState) {
            is Result.Success -> {
                coroutineScope.launch {
                    Toast.makeText(context, "OTP berhasil diverifikasi.", Toast.LENGTH_SHORT).show()
                    delay(1000L)
                    navController.navigate(NavRoute.Login.route) {
                        popUpTo(NavRoute.Register.route) { inclusive = true }
                    }
                }
            }

            is Result.Error -> {
                val errorMsg = (otpState as Result.Error).message
                otpError = when {
                    errorMsg.contains(
                        "Invalid or expired OTP",
                        ignoreCase = true
                    ) -> {
                        val message = "Kode OTP salah atau kadaluarsa."
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        message
                    }

                    errorMsg.contains(
                        "User already exists",
                        ignoreCase = true
                    ) -> {
                        val message = "Username sudah digunakan."
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        message
                    }

                    errorMsg.contains(
                        "Account has been verified",
                        ignoreCase = true
                    ) -> {
                        coroutineScope.launch {
                            Toast.makeText(context, "Akun sudah diverifikasi.", Toast.LENGTH_SHORT)
                                .show()
                            delay(1000L)
                            navController.navigate(NavRoute.Login.route) {
                                popUpTo(NavRoute.Register.route) { inclusive = true }
                            }
                        }
                        null
                    }

                    errorMsg.contains(
                        "kadaluarsa",
                        ignoreCase = true
                    ) -> {
                        val message = "Kode OTP anda kadaluarsa, silahkan kirim ulang OTP."
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        message
                    }

                    else -> {
                        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                        errorMsg
                    }
                }
            }

            else -> otpError = null
        }
    }

    LaunchedEffect(resendOtpState) {
        when (resendOtpState) {
            is Result.Success -> {
                Timber.d("OTP berhasil dikirim ulang.")
                resendTimer = 120
                isResendEnabled = false
                otp = ""
                otpError = null
                Toast.makeText(context, "OTP berhasil dikirim ulang.", Toast.LENGTH_SHORT).show()
            }

            is Result.Error -> {
                Timber.e("Resend OTP gagal: ${(resendOtpState as Result.Error).message}")
                val errorMsg = (resendOtpState as Result.Error).message

                if (errorMsg.contains("Account has been verified", ignoreCase = true)) {
                    Toast.makeText(context, "Akun sudah diverifikasi.", Toast.LENGTH_SHORT).show()
                    coroutineScope.launch {
                        delay(1000L)
                        navController.navigate(NavRoute.Login.route) {
                            popUpTo(NavRoute.Register.route) { inclusive = true }
                        }
                    }
                } else {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
            }
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
                title = "Verifikasi OTP",
                backgroundDrawable = R.drawable.header_isyara
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomInputField(
                    value = otp,
                    onValueChange = { input ->
                        if (input.length <= 6) {
                            otp = input.filter { it.isDigit() }
                            otpError = null
                        }
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
                            otp.isBlank() -> "Kode OTP harus diisi!"
                            otp.length != 6 -> "Kode OTP harus 6 digit!"
                            else -> null
                        }

                        if (otpError == null) {
                            val otpRequest = OtpRequest(otp)
                            viewModel.verifyOtp(token, otpRequest)
                        }
                    },
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
                    Text(text = if (isLoading) "Memproses..." else "Verifikasi Otp")

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Resend OTP
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Tidak menerima OTP? ")
                if (isResendEnabled) {
                    Text(
                        text = "Kirim ulang",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable {
                                Timber.d("User clicked Resend OTP dengan token: $token")
                                viewModel.resendOtp(token)
                                resendTimer = 120
                                isResendEnabled = false
                                otp = ""
                                otpError = null
                            }
                    )
                } else {
                    Text(
                        text = "Tunggu $formattedTime",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            if (resendOtpState is Result.Error && !(resendOtpState as Result.Error).message.contains(
                    "Account has been verified",
                    ignoreCase = true
                )
            ) {
                Text(
                    text = (resendOtpState as Result.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
