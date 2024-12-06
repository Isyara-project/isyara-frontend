package com.application.isyara.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.application.isyara.ui.auth.*

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    // Onboarding screen
    composable(NavRoute.Onboarding.route) {
        OnboardingScreen(navController)
    }

    // Login screen
    composable(NavRoute.Login.route) {
        LoginScreen(navController)
    }

    // Register screen
    composable(NavRoute.Register.route) {
        RegisterScreen(navController)
    }

    composable(NavRoute.Otp.route) {
        OtpScreen(navController, token = "token")
    }

    // Forgot password screen
    composable(NavRoute.ForgotPassword.route) {
        ForgotPasswordScreen(navController)
    }

    // Reset password screen
    composable(NavRoute.ResetPassword.route) {
        ResetPasswordScreen(navController, token = "token")
    }

}
