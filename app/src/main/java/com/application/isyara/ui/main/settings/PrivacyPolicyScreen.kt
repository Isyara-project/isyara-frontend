package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Kebijakan Privasi",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Konten
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PolicyCard(
                title = "Kebijakan Privasi",
                content = """
                    Kami di Isyara berkomitmen untuk melindungi privasi Anda. Kami mengumpulkan data Anda hanya untuk meningkatkan pengalaman pengguna, dan tidak akan membagikan informasi pribadi Anda tanpa izin.
                    
                    Informasi yang kami kumpulkan meliputi:
                    - Data pengguna (nama, email, dll.)
                    - Aktivitas aplikasi
                    
                    Dengan menggunakan aplikasi ini, Anda menyetujui kebijakan privasi ini.
                """.trimIndent()
            )

            PolicyCard(
                title = "Ketentuan Layanan",
                content = """
                    Dengan menggunakan aplikasi Isyara, Anda setuju untuk:
                    - Menggunakan aplikasi ini hanya untuk tujuan pribadi dan non-komersial.
                    - Tidak menyalahgunakan fitur aplikasi untuk aktivitas ilegal atau melanggar hukum.
                    
                    Kami berhak untuk memperbarui kebijakan dan ketentuan ini kapan saja. Harap periksa halaman ini secara berkala.
                """.trimIndent()
            )
        }
    }
}


@Composable
fun PolicyCard(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                text = content,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen(navController = NavController(LocalContext.current))
}
