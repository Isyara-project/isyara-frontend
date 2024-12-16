package com.application.isyara.ui.main.settings

import android.widget.Toast
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.application.isyara.data.model.FeedbackRequest
import com.application.isyara.utils.state.Result
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.isyara.viewmodel.settings.FeedbackViewModel

@Composable
fun FeedbackScreen(
    navController: NavController,
    feedbackViewModel: FeedbackViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }
    val feedbackState = feedbackViewModel.feedbackState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    fun validateFields(): Boolean {
        emailError = when {
            email.isBlank() -> "Email tidak boleh kosong"
            !Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() || !email.endsWith("@gmail.com") -> "Email tidak valid. Pastikan menggunakan @gmail.com"

            else -> null
        }

        descriptionError = if (description.isBlank()) "Deskripsi tidak boleh kosong" else null

        return emailError == null && descriptionError == null
    }

    when (feedbackState) {
        is Result.Loading -> {
            Toast.makeText(context, "Mengirim feedback...", Toast.LENGTH_SHORT).show()
        }

        is Result.Success -> {
            Toast.makeText(context, "Feedback berhasil terkirim!", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }

        is Result.Error -> {
            Toast.makeText(context, "Error: ${feedbackState.message}", Toast.LENGTH_SHORT).show()
        }

        else -> {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        AppHeaderMain(
            title = "Feedback Pengguna",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Kolom Email
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Anda") },
                singleLine = true,
                isError = emailError != null,
                modifier = Modifier.fillMaxWidth()
            )
            emailError?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }

            // Kolom Deskripsi
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Masukan atau Keluhan") },
                isError = descriptionError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            descriptionError?.let {
                Text(text = it, color = Color.Red, fontSize = 12.sp)
            }

            // Tombol Kirim
            Button(
                onClick = {
                    if (validateFields()) {
                        val feedbackRequest = FeedbackRequest(email, description)
                        feedbackViewModel.sendFeedback(feedbackRequest)
                    }
                },
                enabled = validateFields() && feedbackState !is Result.Loading,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (feedbackState is Result.Loading) Color.Gray else Color(
                        0xFF008E9B
                    )
                )
            ) {
                if (feedbackState is Result.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Text(
                        text = "Memproses",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                } else {
                    Text(text = "Kirim", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}
