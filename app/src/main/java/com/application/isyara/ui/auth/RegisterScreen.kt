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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.AppHeaderAuth
import com.application.isyara.utils.auth.CustomInputField

@Composable
fun RegisterScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // State untuk error
    var nameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
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
                placeholder = "Nama kamu",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Nama"
                    )
                },
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
                placeholder = "Username kamu",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_username),
                        contentDescription = "Username"
                    )
                },
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
                placeholder = "Email kamu",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Email"
                    )
                },
                isError = emailError != null,
                errorMessage = emailError
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Input Nomor Handphone
            CustomInputField(
                value = phone,
                onValueChange = {
                    phone = it
                    phoneError = null
                },
                label = "Nomor Handphone",
                placeholder = "Nomor kamu",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = "Nomor Handphone"
                    )
                },
                isError = phoneError != null,
                errorMessage = phoneError
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
                placeholder = "Kata sandi kamu",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Kata Sandi"
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
                isError = passwordError != null,
                errorMessage = passwordError
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Daftar
            Button(
                onClick = {
                    if (name.isBlank()) nameError = "Nama harus diisi!"
                    if (username.isBlank()) usernameError = "Username harus diisi!"
                    if (email.isBlank()) emailError = "Email harus diisi!"
                    if (phone.isBlank()) phoneError = "Nomor Handphone harus diisi!"
                    if (password.isBlank()) passwordError = "Kata sandi harus diisi!"

                    if (nameError == null && usernameError == null && emailError == null && phoneError == null && passwordError == null) {
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Daftar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link ke halaman login
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Sudah punya akun?")
                Spacer(modifier = Modifier.width(4.dp))
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
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        navController = rememberNavController()
    )
}

