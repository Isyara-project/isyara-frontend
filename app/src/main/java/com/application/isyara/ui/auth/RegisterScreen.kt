package com.application.isyara.ui.auth

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.data.model.RegisterRequest
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.AuthViewModel


@Composable
fun RegisterScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // State untuk error
    var nameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // Menggunakan collectAsState untuk mendapatkan state dari StateFlow
    val result by viewModel.registerState.collectAsState()
    val isLoading by viewModel.loadingState.collectAsState()

    // Fungsi untuk validasi email
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Fungsi untuk validasi password
    fun isValidPassword(password: String): Boolean {
        val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{8,}\$".toRegex()
        return regex.matches(password)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AppHeaderAuth(
                title = "Daftar",
                backgroundDrawable = R.drawable.header_isyara
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Input Nama
                CustomInputField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = null
                    },
                    label = "Nama",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Nama"
                        )
                    },
                    placeholder = "Nama kamu",
                    isError = nameError != null,
                    errorMessage = nameError
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Username
                CustomInputField(
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError = null
                    },
                    label = "Username",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_username),
                            contentDescription = "Username"
                        )
                    },
                    placeholder = "Username kamu",
                    isError = usernameError != null,
                    errorMessage = usernameError
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Email
                CustomInputField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = null
                    },
                    label = "Email",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Email"
                        )
                    },
                    placeholder = "Email kamu",
                    isError = emailError != null,
                    errorMessage = emailError
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Kata Sandi
                CustomInputField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = null
                    },
                    label = "Kata Sandi",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = "Kata Sandi"
                        )
                    },
                    placeholder = "Kata sandi kamu",
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        // Menambahkan tombol untuk toggle visibilitas password
                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible }
                        ) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (isPasswordVisible) "Sembunyikan password" else "Tampilkan password"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Daftar
                Button(
                    onClick = {
                        // Validasi sebelum melanjutkan
                        nameError = if (name.isBlank()) "Nama harus diisi!" else null
                        usernameError = if (username.isBlank()) "Username harus diisi!" else null
                        emailError = when {
                            email.isBlank() -> "Email harus diisi!"
                            !isValidEmail(email) -> "Format email tidak valid!"
                            else -> null
                        }
                        passwordError = when {
                            password.isBlank() -> "Kata sandi harus diisi!"
                            !isValidPassword(password) -> "Password tidak sesuai! (Minimal 1 huruf besar, 1 huruf kecil, 1 simbol)"
                            else -> null
                        }

                        // Cek apakah semua error sudah null, jika iya bisa lanjut ke OTP
                        if (nameError == null && usernameError == null && emailError == null && passwordError == null) {
                            viewModel.registerUser(RegisterRequest(name, username, email, password))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Daftar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Menangani state loading dan error
                when (result) {
                    is Result.Error -> {
                        Text(
                            text = (result as Result.Error).message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    is Result.Success -> {
                        val token = (result as Result.Success).data.token
                        navController.navigate("${NavRoute.Otp.route}/$token")
                    }

                    else -> {
                        // Tidak ada tindakan
                    }
                }

                // Link ke halaman login
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sudah punya akun? ")
                    Text(
                        text = "Masuk",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(NavRoute.Login.route)
                        }
                    )
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(16.dp))
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
