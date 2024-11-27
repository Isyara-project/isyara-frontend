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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.isyara.R
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField

@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit,
    onPasswordChangeSuccess: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        AppHeaderAuth(
            title = "Ganti Kata Sandi",
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
                    // Validasi Kata Sandi Baru
                    if (newPassword.isBlank()) {
                        newPasswordError = "Kata sandi baru harus diisi!"
                    } else if (newPassword.length < 8) {
                        newPasswordError = "Kata sandi harus memiliki minimal 8 karakter!"
                    } else if (!newPassword.matches(Regex(".*[A-Z].*"))) {
                        newPasswordError = "Kata sandi harus mengandung huruf besar!"
                    }

                    // Validasi Konfirmasi Kata Sandi
                    if (newPassword != confirmPassword) {
                        confirmPasswordError = "Kata sandi tidak cocok!"
                    }

                    if (newPasswordError == null && confirmPasswordError == null) {
                        onPasswordChangeSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ganti Kata Sandi")
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
