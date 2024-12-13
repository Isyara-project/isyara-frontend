package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun LanguageSettingsScreen(
    navController: NavController,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit
) {
    var selectedLanguage by remember { mutableStateOf(currentLanguage) }

    val languages = listOf("Bahasa Indonesia", "English", "Español", "Français", "Deutsch")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header dengan warna primary
        AppHeaderMain(
            title = "Bahasa Aplikasi",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar Bahasa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            languages.forEach { language ->
                LanguageOption(
                    language = language,
                    isSelected = language == selectedLanguage,
                    onClick = {
                        selectedLanguage = language
                        onLanguageChange(language)
                    }
                )
            }
        }
    }
}


@Composable
fun LanguageOption(
    language: String,
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
            Text(
                text = language,
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

@Preview(showBackground = true)
@Composable
fun LanguageSettingsScreenPreview() {
    LanguageSettingsScreen(
        navController = NavController(LocalContext.current),
        currentLanguage = "Bahasa Indonesia",
        onLanguageChange = { }
    )
}
