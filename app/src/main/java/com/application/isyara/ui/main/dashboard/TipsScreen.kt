package com.application.isyara.ui.main.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.R
import com.application.isyara.utils.main.AppHeaderMain

data class TipItem(val iconRes: Int, val title: String, val description: String)

@Composable
fun TipsScreen(onBackClick: () -> Unit, navController: NavController) {
    val tipsList = listOf(
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = "Belajar dengan Konsisten",
            description = "Luangkan waktu 30 menit setiap hari untuk belajar bahasa isyarat."
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = "Praktek dengan Teman",
            description = "Latih bahasa isyarat dengan teman atau keluarga agar lebih percaya diri."
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = "Gunakan Aplikasi Isyara",
            description = "Eksplor fitur translate dan kamus di aplikasi Isyara untuk mempermudah belajar."
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = "Tonton Video dan Konten Online",
            description = "Belajar dari video tutorial bahasa isyarat di YouTube atau platform lainnya."
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = "Ikut Komunitas Bahasa Isyarat",
            description = "Bergabung dengan komunitas atau forum untuk belajar bersama dan berbagi pengalaman."
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = "Gunakan Cermin",
            description = "Berlatih bahasa isyarat di depan cermin untuk melihat gerakan tangan dan ekspresi wajah Anda."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Tips Belajar",
            onBackClick = onBackClick,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar Tips
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tipsList) { tip ->
                TipCard(tip)
            }
        }
    }
}

@Composable
fun TipCard(tip: TipItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color.White),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = tip.iconRes),
                contentDescription = tip.title,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF008E9B)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = tip.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}