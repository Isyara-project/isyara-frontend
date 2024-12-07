package com.application.isyara.ui.main.settings

import android.widget.Toast
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.viewmodel.auth.AuthViewModel
import com.application.isyara.data.model.FeedbackRequest
import com.application.isyara.utils.auth.Result
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FeedbackScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    // State untuk input email dan deskripsi
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // State untuk menampilkan status loading dan feedback response
    val feedbackState = authViewModel.feedbackState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    // Menangani feedback pengiriman
    when (feedbackState) {
        is Result.Loading -> {
            // Menampilkan loading ketika sedang memproses pengiriman feedback
            Toast.makeText(context, "Mengirim feedback...", Toast.LENGTH_SHORT).show()
        }
        is Result.Success -> {
            // Menampilkan pesan sukses saat feedback terkirim
            Toast.makeText(context, "Feedback berhasil terkirim!", Toast.LENGTH_SHORT).show()
        }
        is Result.Error -> {
            // Menampilkan pesan kesalahan jika ada masalah dalam mengirim feedback
            Toast.makeText(context, "Error: ${feedbackState.message}", Toast.LENGTH_SHORT).show()
        }
        else -> {
            // Tidak ada state yang aktif, biarkan tetap kosong
        }
    }

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
                    val feedbackRequest = FeedbackRequest(email, description)
                    authViewModel.sendFeedback(feedbackRequest) // Mengirim feedback melalui ViewModel
                    navController.popBackStack() // Navigasi kembali setelah mengirim feedback
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
        authViewModel = hiltViewModel()
    )
}
