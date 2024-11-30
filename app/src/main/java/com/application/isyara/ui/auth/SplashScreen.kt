package com.application.isyara.ui.auth

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute

@Composable
fun SplashScreen(navController: NavHostController) {
    // Menampilkan splash screen selama 3 detik, kemudian navigasi ke Onboarding atau halaman utama
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(NavRoute.Onboarding.route) {
                popUpTo(NavRoute.Onboarding.route) { inclusive = true } // Menutup splash screen
            }
        }, 3000) // 3 detik
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0077EE) // Blue80
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.isyara_splash),
                contentDescription = "Splash Image",
                modifier = Modifier
                    .fillMaxWidth(0.6f) // Mengatur ukuran gambar, sesuaikan dengan kebutuhan
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = NavHostController(context = LocalContext.current)) // Gunakan controller yang sesuai di preview
}
