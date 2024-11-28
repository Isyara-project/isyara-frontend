package com.application.isyara.utils.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.isyara.R

@Composable
fun AppHeaderMain(
    title: String = "Isyara",
    backgroundColor: Brush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Transparent.copy(alpha = 0.0f))
    ),
    modifier: Modifier = Modifier,
    showDashboardElements: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onNotificationClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null,
    onHelpClick: (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    paddingStart: Dp = 16.dp,
    paddingEnd: Dp = 16.dp,
    isTopLevelPage: Boolean = false

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(backgroundColor)
            .padding(start = paddingStart, end = paddingEnd),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tombol Kembali atau Logo Isyara
        if (onBackClick != null && !isTopLevelPage) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                // Judul untuk Non-Dashboard
                if (!showDashboardElements) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }
        } else if (isTopLevelPage) {
            // Judul tanpa tombol back untuk Top-Level page
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        } else if (showDashboardElements) {
            // Logo Kustom untuk Dashboard
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.isyara_putih),
                    contentDescription = "Logo Isyara",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Isyara",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Elemen Khusus Dashboard
        if (showDashboardElements) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = { onSearchClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = { onNotificationClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = { onProfileClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = Color.Black
                    )
                }
            }
        }

        // Tombol Help di Kanan (opsional)
        if (onHelpClick != null) {
            IconButton(
                onClick = onHelpClick,
                modifier = Modifier.padding(end = if (trailingContent != null) 48.dp else 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                    contentDescription = "Help",
                    tint = Color.White
                )
            }
        }

        // Konten Tambahan di Kanan (opsional)
        if (trailingContent != null) {
            Box(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                trailingContent()
            }
        }
    }
}

