package com.application.isyara.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.application.isyara.navigation.AppMainNavGraph
import com.application.isyara.theme.IsyaraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsyaraTheme {
                val navController = rememberNavController()
                AppMainNavGraph(navController)
            }
        }
    }
}
