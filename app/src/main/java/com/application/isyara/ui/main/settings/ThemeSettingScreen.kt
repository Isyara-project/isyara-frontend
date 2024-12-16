package com.application.isyara.ui.main.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.isyara.ui.restartApp
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.settings.AppTheme
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.ThemeViewModel

@Composable
fun ThemeSettingsScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel = hiltViewModel(),
    activity: ComponentActivity
) {
    val themeState by themeViewModel.themeState.collectAsState()
    val themes = listOf(
        AppTheme.LIGHT,
        AppTheme.DARK,
        AppTheme.SYSTEM_DEFAULT
    )

    var showRestartDialog by remember { mutableStateOf(false) }
    var selectedTheme by remember { mutableStateOf<AppTheme?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        AppHeaderMain(
            title = "Tema Aplikasi",
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            ),
            logoColor = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (themeState) {
            is Result.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
            is Result.Success -> {
                val currentTheme = (themeState as Result.Success<AppTheme>).data

                // Tampilkan daftar tema
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    themes.forEach { theme ->
                        ThemeOptionRow(
                            theme = theme,
                            isSelected = theme == currentTheme,
                            onSelect = {
                                selectedTheme = theme
                                themeViewModel.updateTheme(theme)
                                showRestartDialog = true
                            }
                        )
                    }
                }
            }
            is Result.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (themeState as Result.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 16.sp
                    )
                }
            }
            else -> {
                // Default or empty UI
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    // Dialog untuk merestart aplikasi
    if (showRestartDialog && selectedTheme != null) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "Restart Aplikasi") },
            text = { Text(text = "Aplikasi perlu direstart agar perubahan tema diterapkan.") },
            confirmButton = {
                TextButton(onClick = {
                    restartApp(activity)
                }) {
                    Text("Restart")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showRestartDialog = false
                }) {
                    Text("Batal")
                }
            }
        )
    }
}


@Composable
fun ThemeOptionRow(
    theme: AppTheme,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = when (theme) {
                AppTheme.LIGHT -> Icons.Default.LightMode
                AppTheme.DARK -> Icons.Default.DarkMode
                AppTheme.SYSTEM_DEFAULT -> Icons.Default.Settings
            },
            contentDescription = theme.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = when (theme) {
                AppTheme.LIGHT -> "Light Mode"
                AppTheme.DARK -> "Dark Mode"
                AppTheme.SYSTEM_DEFAULT -> "System Default"
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
