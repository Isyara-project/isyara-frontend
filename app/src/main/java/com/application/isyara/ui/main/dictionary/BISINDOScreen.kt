package com.application.isyara.ui.main.dictionary

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun BISINDOScreen(navController: NavController) {
    AlphabetScreen(
        title = "Bisindo Dictionary",
        descriptionTitle = "Apa itu Bisindo?",
        description = "BISINDO (Bahasa Isyarat Indonesia) adalah bahasa isyarat alami yang digunakan oleh komunitas tunarungu di Indonesia. BISINDO berfokus pada komunikasi visual yang intuitif dan mudah dipahami.",
        alphabetTitle = "Alfabet BISINDO",
        alphabetList = ('A'..'Z').map { it.toString() },
        navController = navController
    )
}
