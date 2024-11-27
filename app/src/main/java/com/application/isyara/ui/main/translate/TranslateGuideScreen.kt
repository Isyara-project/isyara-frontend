package com.application.isyara.ui.main.translate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun TranslateGuideScreen(onBackClick: () -> Unit, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {

        // Header menggunakan AppHeaderMain
        AppHeaderMain(
            title = "Panduan Penggunaan Kamera",
            onBackClick = onBackClick,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Konten Panduan
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GuideStepCard(
                stepNumber = 1,
                title = "Posisikan Kamera",
                description = "Pastikan tangan Anda berada di depan kamera dan terlihat jelas. Hindari gerakan tangan yang terlalu cepat agar sistem dapat mendeteksi isyarat dengan baik."
            )
            GuideStepCard(
                stepNumber = 2,
                title = "Cahaya yang Cukup",
                description = "Gunakan kamera di tempat dengan pencahayaan yang baik untuk menghindari kesalahan deteksi."
            )
            GuideStepCard(
                stepNumber = 3,
                title = "Tekan Tombol Kamera",
                description = "Tekan tombol kamera untuk memulai proses penerjemahan bahasa isyarat ke teks. Tunggu hingga hasil muncul di layar."
            )
            GuideStepCard(
                stepNumber = 4,
                title = "Gunakan Satu Tangan",
                description = "Pastikan hanya satu tangan yang digunakan untuk bahasa isyarat, kecuali jika isyarat memerlukan dua tangan."
            )
        }
    }
}

@Composable
fun GuideStepCard(stepNumber: Int, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Langkah $stepNumber",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF008E9B)
            )
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Justify
            )
        }
    }
}