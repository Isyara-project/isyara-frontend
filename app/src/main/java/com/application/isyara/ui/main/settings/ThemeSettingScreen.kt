package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun ThemeSettingsScreen(
    navController: NavController,
    currentTheme: String,
    onThemeChange: (String) -> Unit
) {
    var selectedTheme by remember { mutableStateOf(currentTheme) }

    val themes = listOf(
        ThemeOption("Light Mode", Icons.Default.LightMode),
        ThemeOption("Dark Mode", Icons.Default.DarkMode),
        ThemeOption("System Default", Icons.Default.Settings)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Tema Aplikasi",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar Tema
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            themes.forEach { theme ->
                ThemeOptionCard(
                    theme = theme,
                    isSelected = theme.name == selectedTheme,
                    onClick = {
                        selectedTheme = theme.name
                        onThemeChange(theme.name)
                    }
                )
            }
        }
    }
}


@Composable
fun ThemeOptionCard(
    theme: ThemeOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
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
                imageVector = theme.icon,
                contentDescription = theme.name,
                tint = Color(0xFF008E9B),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = theme.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color(0xFF008E9B)
                )
            }
        }
    }
}

data class ThemeOption(
    val name: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Preview(showBackground = true)
@Composable
fun ThemeSettingsScreenPreview() {
    ThemeSettingsScreen(
        navController = NavController(LocalContext.current),
        currentTheme = "System Default",
        onThemeChange = {  }
    )
}
