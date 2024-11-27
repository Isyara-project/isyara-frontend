package com.application.isyara.ui.main.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.isyara.R
import com.application.isyara.utils.main.AppHeaderMain

data class NotificationItem(
    val iconRes: Int,
    val title: String,
    val description: String,
    val timestamp: String
)

@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit,
    notifications: List<NotificationItem> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Notifications",
            onBackClick = onBackClick,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF008E9B),
                    Color(0xFF00B4D8)
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (notifications.isNotEmpty()) {
            // List of Notifications
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notifications) { notification ->
                    NotificationCard(notification)
                }
            }
        } else {
            // Empty State
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "No Notifications",
                        modifier = Modifier.size(64.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Belum ada notifikasi.\nPantau terus untuk informasi terbaru!",
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
fun NotificationCard(notification: NotificationItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
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
                painter = painterResource(id = notification.iconRes),
                contentDescription = notification.title,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF008E9B)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = notification.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.timestamp,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    val dummyNotifications = listOf(
        NotificationItem(
            iconRes = R.drawable.ic_notification,
            title = "Update Aplikasi",
            description = "Versi baru Isyara telah tersedia! Perbarui sekarang untuk fitur terbaru.",
            timestamp = "10 menit lalu"
        ),
        NotificationItem(
            iconRes = R.drawable.ic_notification,
            title = "Sesi Pelatihan",
            description = "Sesi pelatihan bahasa isyarat akan dimulai pukul 15:00.",
            timestamp = "2 jam lalu"
        ),
        NotificationItem(
            iconRes = R.drawable.ic_notification,
            title = "Tips Baru",
            description = "Tips belajar bahasa isyarat terbaru sudah tersedia.",
            timestamp = "Kemarin, 14:00"
        )
    )

    NotificationsScreen(
        onBackClick = { /* Handle Back */ },
        notifications = dummyNotifications
    )
}
