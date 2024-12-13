package com.application.isyara.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.viewmodel.auth.AuthViewModel
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun SplashScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    val tokenState by viewModel.sessionManager.tokenFlow.collectAsState(initial = null)

    LaunchedEffect(key1 = true) {
        viewModel.checkUserLoggedIn()
        delay(2000)
        if (tokenState != null) {
            Timber.d("Token exists: Navigating to Dashboard")
            navController.navigate(NavRoute.Dashboard.route) {
                popUpTo(NavRoute.Splash.route) { inclusive = true }
            }
        } else {
            Timber.d("No token: Navigating to Onboarding")
            navController.navigate(NavRoute.Onboarding.route) {
                popUpTo(NavRoute.Splash.route) { inclusive = true }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0077EE)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.isyara_splash),
                contentDescription = "Splash Image",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(16.dp)
            )
        }
    }
}