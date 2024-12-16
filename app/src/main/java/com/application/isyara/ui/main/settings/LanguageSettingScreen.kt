package com.application.isyara.ui.main.settings

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.isyara.R
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.state.Result
import java.util.*

fun openLanguageSettings(context: Context) {
    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
    context.startActivity(intent)
}

@Composable
fun LanguageSettingsScreen(
    navController: NavController,
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val languageState by languageViewModel.languageState.collectAsState()
    val setLanguageState by languageViewModel.setLanguageState.collectAsState()

    var selectedLanguage by remember { mutableStateOf<String?>(null) }

    val languages = listOf(
        stringResource(R.string.english),
        stringResource(R.string.indonesia),
        stringResource(R.string.espa_ol)
    )

    val context = LocalContext.current

    LaunchedEffect(languageState) {
        when (languageState) {
            is Result.Success -> {
                selectedLanguage = (languageState as Result.Success<String>).data
            }

            is Result.Error -> {
            }

            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        AppHeaderMain(
            title = stringResource(R.string.application_language),
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                        openLanguageSettings(context)
                    }
                )
            }
        }

        when (setLanguageState) {
            is Result.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            is Result.Success -> {
                LaunchedEffect(setLanguageState) {
                    Toast.makeText(context, "Language updated", Toast.LENGTH_SHORT).show()
                    (context as? Activity)?.recreate()
                }
            }

            is Result.Error -> {
                LaunchedEffect(setLanguageState) {
                }
            }

            else -> {}
        }
    }
}

fun languageCodeFromName(language: String): String {
    return when (language) {
        "English" -> "en"
        "Bahasa Indonesia" -> "id"
        "Español" -> "es"
        else -> "en"
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
