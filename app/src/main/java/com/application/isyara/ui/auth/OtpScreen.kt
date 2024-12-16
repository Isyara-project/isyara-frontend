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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    val context = LocalContext.current
    val otpState by viewModel.otpState.collectAsState()
    val resendOtpState by viewModel.resendOtpState.collectAsState()
    val minutes = resendTimer / 60
    val seconds = resendTimer % 60
    val formattedTime = String.format(
        Locale.getDefault(),
        context.getString(R.string.format_time),
        minutes,
        seconds
    )
    val coroutineScope = rememberCoroutineScope()
    val isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(resendTimer) {
        if (!isResendEnabled && resendTimer > 0) {
            delay(1000L)
            resendTimer -= 1
        } else if (resendTimer <= 0 && !isResendEnabled) {
            isResendEnabled = true
            otpError = context.getString(R.string.otp_has_expired)
        }
    }

    LaunchedEffect(otpState) {
        when (otpState) {
            is Result.Success -> {
                coroutineScope.launch {
                    Toast.makeText(
                        context,
                        context.getString(R.string.otp_successfully_verified), Toast.LENGTH_SHORT
                    ).show()
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
                        val message = context.getString(R.string.otp_wrong_or_expired)
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        message
                    }

                    errorMsg.contains(
                        "User already exists",
                        ignoreCase = true
                    ) -> {
                        val message = context.getString(R.string.username_or_email_already_exist)
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        message
                    }

                    errorMsg.contains(
                        "Account has been verified",
                        ignoreCase = true
                    ) -> {
                        coroutineScope.launch {
                            Toast.makeText(
                                context,
                                context.getString(R.string.account_already_verified),
                                Toast.LENGTH_SHORT
                            )
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
                        val message = context.getString(R.string.otp_expired_resend_otp)
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
                resendTimer = 120
                isResendEnabled = false
                otp = ""
                otpError = null
                Toast.makeText(
                    context,
                    context.getString(R.string.otp_success_resend), Toast.LENGTH_SHORT
                ).show()
            }

            is Result.Error -> {
                val errorMsg = (resendOtpState as Result.Error).message

                if (errorMsg.contains("Account has been verified", ignoreCase = true)) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.account_already_verified),
                        Toast.LENGTH_SHORT
                    ).show()
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
                title = stringResource(R.string.verify_otp),
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
                    label = stringResource(R.string.input_otp),
                    placeholder = stringResource(R.string.input_otp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_otp),
                            contentDescription = stringResource(R.string.verify_otp)
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
                            otp.isBlank() -> context.getString(R.string.otp_must_filled)
                            otp.length != 6 -> context.getString(R.string.otp_must_6)
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
                    Text(
                        text = if (isLoading) context.getString(R.string.process) else context.getString(
                            R.string.verify_otp
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.not_receiving_otp))
                if (isResendEnabled) {
                    Text(
                        text = stringResource(R.string.resend_otp),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable {
                                viewModel.resendOtp(token)
                                resendTimer = 120
                                isResendEnabled = false
                                otp = ""
                                otpError = null
                            }
                    )
                } else {
                    Text(
                        text = stringResource(R.string.wait, formattedTime),
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
