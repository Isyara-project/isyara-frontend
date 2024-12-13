package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
fun AboutIsyaraScreen(onBackClick: () -> Unit, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Tentang Isyara",
            onBackClick = onBackClick,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Konten Tentang Isyara
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AboutCard(
                title = "Apa itu Isyara?",
                description = "Isyara adalah aplikasi yang dirancang untuk membantu pengguna mempelajari dan menerjemahkan bahasa isyarat. Aplikasi ini bertujuan untuk meningkatkan komunikasi antara komunitas tunarungu dan masyarakat umum."
            )

            AboutCard(
                title = "Fitur Utama",
                description = """
                    - Translate bahasa isyarat secara real-time menggunakan kamera.
                    - Riwayat aktivitas untuk melacak fitur yang telah digunakan.
                    - Pengaturan aplikasi yang mudah untuk menyesuaikan kebutuhan pengguna.
                """.trimIndent()
            )

            AboutCard(
                title = "Misi Kami",
                description = "Kami berkomitmen untuk mendukung inklusi sosial dengan menyediakan alat yang memudahkan komunikasi lintas komunitas."
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Footer
        Text(
            text = "Versi Aplikasi: 1.0.0",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun AboutCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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