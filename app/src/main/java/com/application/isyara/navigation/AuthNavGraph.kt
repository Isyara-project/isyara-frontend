package com.application.isyara.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.application.isyara.ui.auth.ForgotPasswordScreen
import com.application.isyara.ui.auth.LoginScreen
import com.application.isyara.ui.auth.OnboardingScreen
import com.application.isyara.ui.auth.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    composable(NavRoute.Onboarding.route) {
        OnboardingScreen(navController)
    }
    composable(NavRoute.Login.route) {

        LoginScreen(navController)
    }
    composable(NavRoute.Register.route) {
        RegisterScreen(navController)
    }
    composable(NavRoute.ForgotPassword.route) {
        ForgotPasswordScreen(navController)
    }
}
