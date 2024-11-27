package com.application.isyara.utils.auth

import com.application.isyara.R

data class OnboardingPage(
    val image: Int,
    val title: String,
    val description: String
) {
    companion object {
        val pages = listOf(
            OnboardingPage(
                image = R.drawable.onboarding_image_1,
                title = "Mulai dari awal dengan kamus",
                description = "Kamu juga bisa mulai belajar dari awal dengan kamus yang sudah disediakan secara gratis!"
            ),
            OnboardingPage(
                image = R.drawable.onboarding_image_2,
                title = "Terjemahkan kalimat",
                description = "Kamu dapat menggunakan fitur terjemah untuk meningkatkan kemampuan kamu."
            )
        )
    }
}



