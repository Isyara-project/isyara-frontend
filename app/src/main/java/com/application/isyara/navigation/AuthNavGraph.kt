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

    composable(
        route = "${NavRoute.Otp.route}/{token}",
        arguments = listOf(
            navArgument("token") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val token = backStackEntry.arguments?.getString("token")
        if (token != null) {
            OtpScreen(navController, token = token)
        } else {
            navController.popBackStack()
        }
    }

    composable(NavRoute.ForgotPassword.route) {
        ForgotPasswordScreen(navController = navController)
    }

    composable(
        route = "${NavRoute.ResetPassword.route}/{token}",
        arguments = listOf(navArgument("token") { type = NavType.StringType })
    ) { backStackEntry ->
        val token = backStackEntry.arguments?.getString("token") ?: ""
        ResetPasswordScreen(token = token, navController = navController)
    }

}
