package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun FeedbackScreen(
    navController: NavController,
    onSendClick: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header dengan warna primary
        AppHeaderMain(
            title = "Feedback Pengguna",
            onBackClick = { navController.popBackStack() }, // Gunakan NavController untuk kembali
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Kolom Email
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Anda") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Kolom Deskripsi
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Masukan atau Keluhan") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Kirim
            Button(
                onClick = {
                    onSendClick(email, description)
                    navController.popBackStack()
                },
                enabled = email.isNotBlank() && description.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
            ) {
                Text(text = "Kirim", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FeedbackScreenPreview() {
    FeedbackScreen(
        navController = NavController(LocalContext.current),
        onSendClick = { _, _ -> /* Handle Send Action */ }
    )
}
