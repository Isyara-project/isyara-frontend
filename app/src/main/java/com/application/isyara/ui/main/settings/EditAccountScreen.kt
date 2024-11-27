package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun EditAccountScreen(
    navController: NavController,
    onSaveClick: (String, String, String) -> Unit,
) {
    var name by remember { mutableStateOf("Nama Pengguna") }
    var email by remember { mutableStateOf("user@isyara.com") }
    var phone by remember { mutableStateOf("081234567890") }
    var password by remember { mutableStateOf("********") }
    var newPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Edit Akun",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF008E9B),
                    Color(0xFF00B4D8)
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Foto Profil di tengah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFFE1F5FE), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Foto Profil",
                    modifier = Modifier.size(80.dp),
                    tint = Color(0xFF008E9B)
                )
            }
        }

        // Form Input
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nama Lengkap") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = email,
                onValueChange = {},
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Nomor Telepon") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = {},
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                visualTransformation = PasswordVisualTransformation()
            )

            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("Password Baru") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Sembunyikan Password" else "Tampilkan Password"
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Simpan Perubahan
        Button(
            onClick = {
                onSaveClick(name, phone, newPassword)
                navController.popBackStack()
            },
            enabled = name.isNotBlank() && phone.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
        ) {
            Text(text = "Simpan Perubahan", fontSize = 16.sp, color = Color.White)
        }
    }
}