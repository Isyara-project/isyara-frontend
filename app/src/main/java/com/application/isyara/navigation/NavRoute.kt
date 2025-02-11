package com.application.isyara.navigation

import android.net.Uri

sealed class NavRoute(val route: String) {
    // Auth Screens
    object Splash : NavRoute("splash")  // Rute Splash Screen
    object Onboarding : NavRoute("onboarding")
    object Login : NavRoute("login")
    object Register : NavRoute("register")
    object Otp : NavRoute("otp")
    object ForgotPassword : NavRoute("forgot_password")
    object ResetPassword : NavRoute("reset_password")
    object ChangePassword : NavRoute("change_password")

    // Bottom Navigation Screens
    object Dashboard : NavRoute("dashboard")
    object Translate : NavRoute("translate")
    object History : NavRoute("history")
    object Settings : NavRoute("settings")

    // Dashboard
    object Search : NavRoute("search")
    object Notification : NavRoute("notification")
    object TipsBelajar : NavRoute("tips_belajar")

    // Dictionary
    object Dictionary : NavRoute("dictionary")
    object SibiPicture : NavRoute("sibi_picture")
    object PictureDetail : NavRoute("picture_detail/{imageUrl}")
    object SibiVideo : NavRoute("sibi_video")
    object VideoPlayer : NavRoute("videoPlayer/{videoUrl}") {
        fun createRoute(videoUrl: String) = "videoPlayer/${Uri.encode(videoUrl)}"
    }


    // Translate
    object TranslateGuide : NavRoute("translate_guide")

    // History


    // Settings
    object EditAccount : NavRoute("edit_account")
    object LanguageSettings : NavRoute("language_settings")
    object ThemeSettings : NavRoute("theme_settings")
    object About : NavRoute("about")
    object Feedback : NavRoute("feedback")
    object PrivacyPolicy : NavRoute("privacy_policy")


}
