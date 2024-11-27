package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        AppHeaderMain(
            title = "Pengaturan",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
            ),
            showDashboardElements = false,
            isTopLevelPage = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profil Pengguna
        ProfileSection {
            navController.navigate(NavRoute.EditAccount.route)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Pengaturan Lainnya
        SettingsOption(
            title = "Bahasa Aplikasi",
            icon = Icons.Default.Language,
            onClick = { navController.navigate(NavRoute.LanguageSettings.route) }
        )

        SettingsOption(
            title = "Tema Aplikasi",
            icon = Icons.Default.ModeNight,
            onClick = { navController.navigate(NavRoute.ThemeSettings.route) }
        )

        SettingsOption(
            title = "Tentang Isyara",
            icon = Icons.Default.Info,
            onClick = { navController.navigate(NavRoute.About.route) }
        )

        SettingsOption(
            title = "Feedback Pengguna",
            icon = Icons.Default.Feedback,
            onClick = { navController.navigate(NavRoute.Feedback.route) }
        )

        SettingsOption(
            title = "Kebijakan Privasi & Ketentuan Layanan",
            icon = Icons.Default.Policy,
            onClick = { navController.navigate(NavRoute.PrivacyPolicy.route) }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Logout
        SettingsOption(
            title = "Keluar",
            icon = Icons.Default.Logout,
            onClick = { navController.navigate(NavRoute.Login.route) },
            isDestructive = true
        )
    }
}

@Composable
fun ProfileSection(onEditAccountClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFE1F5FE), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Foto Profil",
                modifier = Modifier.size(64.dp),
                tint = Color(0xFF008E9B)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nama Pengguna",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "user@isyara.com",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEditAccountClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
        ) {
            Text(
                text = "Edit Akun",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun SettingsOption(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isDestructive) Color.Red else Color(0xFF008E9B),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDestructive) Color.Red else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        navController = NavController(LocalContext.current)
    )
}
