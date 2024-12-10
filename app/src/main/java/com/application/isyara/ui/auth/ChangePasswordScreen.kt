package com.application.isyara.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.isyara.R
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.viewmodel.auth.AuthViewModel
import com.application.isyara.utils.auth.Result


@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit,
    onPasswordChangeSuccess: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    // Variables for UI input
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    // Variables for validation errors
    var oldPasswordError by remember { mutableStateOf<String?>(null) }
    var newPasswordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    // UI error and loading states from ViewModel
    val changePasswordState by viewModel.changePasswordState.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        AppHeaderAuth(
            title = "Ganti Kata Sandi",
            backgroundDrawable = R.drawable.header_isyara
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Input Kata Sandi Lama
            CustomInputField(
                value = oldPassword,
                onValueChange = {
                    oldPassword = it
                    oldPasswordError = null
                },
                label = "Kata Sandi Lama",
                placeholder = "Masukkan kata sandi lama",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Kata Sandi Lama"
                    )
                },
                trailingIcon = {},
                visualTransformation = PasswordVisualTransformation(),
                isError = oldPasswordError != null,
                errorMessage = oldPasswordError
            )

            // Input Kata Sandi Baru
            CustomInputField(
                value = newPassword,
                onValueChange = {
                    newPassword = it
                    newPasswordError = null
                },
                label = "Kata Sandi Baru",
                placeholder = "Masukkan kata sandi baru",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Kata Sandi Baru"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = newPasswordError != null,
                errorMessage = newPasswordError
            )

            // Input Konfirmasi Kata Sandi
            CustomInputField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = null
                },
                label = "Konfirmasi Kata Sandi",
                placeholder = "Masukkan ulang kata sandi baru",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Konfirmasi Kata Sandi"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                        Icon(
                            imageVector = if (isConfirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = confirmPasswordError != null,
                errorMessage = confirmPasswordError
            )

            // Tombol Ganti Kata Sandi
            Button(
                onClick = {
                    // Validasi Input
                    if (oldPassword.isBlank()) {
                        oldPasswordError = "Kata sandi lama harus diisi!"
                    }
                    if (newPassword.isBlank()) {
                        newPasswordError = "Kata sandi baru harus diisi!"
                    } else if (newPassword.length < 8) {
                        newPasswordError = "Kata sandi harus memiliki minimal 8 karakter!"
                    } else if (!newPassword.matches(Regex(".*[A-Z].*"))) {
                        newPasswordError = "Kata sandi harus mengandung huruf besar!"
                    }

                    if (newPassword != confirmPassword) {
                        confirmPasswordError = "Kata sandi tidak cocok!"
                    }

                    if (oldPasswordError == null && newPasswordError == null && confirmPasswordError == null) {
                        viewModel.changePassword(oldPassword, newPassword)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ganti Kata Sandi")
            }

            // Loading indicator
            if (loadingState) {
                // Show loading spinner or message
                Text(text = "Mengubah Kata Sandi...", modifier = Modifier.fillMaxWidth())
            }

            // Menampilkan pesan error jika terjadi
            when (val result = changePasswordState) {
                is Result.Error -> {
                    Text(text = result.message ?: "Gagal mengubah kata sandi", color = Color.Red)
                }
                is Result.Success -> {
                    onPasswordChangeSuccess()
                    Text(text = result.data.message ?: "Password changed successfully.")
                }
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(
        onBackClick = {},
        onPasswordChangeSuccess = {}
    )
}
