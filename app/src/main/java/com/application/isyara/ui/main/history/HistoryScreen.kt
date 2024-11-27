package com.application.isyara.ui.main.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.application.isyara.utils.main.HistoryItem

@Composable
fun HistoryScreen(
    navController: NavController,
    historyItems: List<HistoryItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Histori Aktivitas",
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF008E9B),
                    Color(0xFF00B4D8)
                )
            ),
            trailingContent = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus Semua",
                        tint = Color.White
                    )
                }
            },
            showDashboardElements = false,
            isTopLevelPage = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Konten Daftar Riwayat
        if (historyItems.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(historyItems) { item ->
                    HistoryCard(item)
                }
            }
        } else {
            // Pesan Jika Tidak Ada Riwayat
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "No History",
                        tint = Color.Gray,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Belum ada aktivitas.\nJelajahi fitur Isyara sekarang!",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryCard(item: HistoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = Color(0xFF008E9B),
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = item.time,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    val dummyData = listOf(
        HistoryItem(
            icon = Icons.Default.History,
            title = "Membuka Translate",
            time = "10 menit yang lalu",
            onClick = {}
        ),
        HistoryItem(
            icon = Icons.Default.History,
            title = "Membuka kamus offline",
            time = "Kemarin, 14:30",
            onClick = {}
        )
    )

    HistoryScreen(
        navController = NavController(LocalContext.current),
        historyItems = dummyData
    )
}
