package com.application.isyara.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.application.isyara.navigation.AppMainNavGraph
import com.application.isyara.theme.IsyaraTheme
import com.application.isyara.utils.settings.AppTheme
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsyaraApp()
        }
    }
}

@Composable
fun IsyaraApp(themeViewModel: ThemeViewModel = hiltViewModel()) {
    val themeState by themeViewModel.themeState.collectAsState()

    LaunchedEffect(themeState) {
        when (themeState) {
            is Result.Success -> {
                val currentTheme = (themeState as Result.Success<AppTheme>).data
                Timber.d("Current Theme: $currentTheme")
            }

            is Result.Error -> {
                val errorMessage = (themeState as Result.Error).message
                Timber.e("Error Loading Theme: $errorMessage")
            }

            else -> {}
        }
    }

    val isDarkTheme = when (themeState) {
        is Result.Success -> {
            when ((themeState as Result.Success<AppTheme>).data) {
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
                AppTheme.SYSTEM_DEFAULT -> isSystemInDarkTheme()
            }
        }

        else -> {
            isSystemInDarkTheme()
        }
    }

    LaunchedEffect(isDarkTheme) {
        Timber.d("Applying isDarkTheme: $isDarkTheme")
    }

    IsyaraTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()
        AppMainNavGraph(navController)
    }
}

fun restartApp(activity: ComponentActivity) {
    val intent = activity.packageManager.getLaunchIntentForPackage(activity.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    activity.finish()
    activity.startActivity(intent)
    Runtime.getRuntime().exit(0)
}
