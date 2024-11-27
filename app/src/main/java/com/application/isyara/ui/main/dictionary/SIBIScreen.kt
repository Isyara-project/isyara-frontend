package com.application.isyara.ui.main.dictionary

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SIBIScreen(navController: NavController) {
    AlphabetScreen(
        title = "Sibi Dictionary",
        descriptionTitle = "Apa itu Sibi?",
        description = "SIBI (Sistem Isyarat Bahasa Indonesia) adalah bahasa isyarat yang digunakan oleh komunitas tunarungu di Indonesia. SIBI dirancang untuk memberikan representasi tata bahasa Indonesia dalam bentuk isyarat.",
        alphabetTitle = "Alfabet SIBI",
        alphabetList = ('A'..'Z').map { it.toString() },
        navController = navController
    )
}
