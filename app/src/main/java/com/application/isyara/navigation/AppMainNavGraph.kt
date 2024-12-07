package com.application.isyara.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.application.isyara.ui.auth.ResetPasswordScreen
import com.application.isyara.ui.auth.SplashScreen
import com.application.isyara.ui.main.dashboard.*
import com.application.isyara.ui.main.dictionary.*
import com.application.isyara.ui.main.history.HistoryScreen
import com.application.isyara.ui.main.settings.*
import com.application.isyara.ui.main.translate.*
import com.application.isyara.utils.main.NavigationItem

@Composable
fun AppMainNavGraph(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val shouldShowBottomNav = when (currentRoute) {
        NavRoute.Splash.route,
        NavRoute.Onboarding.route,
        NavRoute.Login.route,
        NavRoute.Register.route,
        NavRoute.Otp.route,
        NavRoute.ForgotPassword.route,
        NavRoute.ResetPassword.route -> false
        else -> true
    }


    Scaffold(
        bottomBar = {
            if (shouldShowBottomNav) {
                CustomBottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoute.Splash.route, // Splash screen
                modifier = Modifier.fillMaxSize()
            ) {
                // Splash screen
                composable(NavRoute.Splash.route) {
                    SplashScreen(navController)
                }

                // Auth navigation graph
                authNavGraph(navController)

                composable(
                    route = "${NavRoute.ResetPassword.route}/{token}",
                    arguments = listOf(navArgument("token") { type = NavType.StringType })
                ) { backStackEntry ->
                    val token = backStackEntry.arguments?.getString("token") ?: ""
                    ResetPasswordScreen(navController, token = token)
                }




                // Main screens
                composable(NavRoute.Dashboard.route) {
                    DashboardScreen(
                        userName = "Zacky",
                        onSearchClick = { navController.navigate(NavRoute.Search.route) },
                        navController = navController
                    )
                }
                composable(NavRoute.Search.route) {
                    SearchScreen(
                        onBackClick = { navController.popBackStack() },
                        searchResults = listOf()
                    )
                }
                composable(NavRoute.Notification.route) {
                    NotificationsScreen(
                        onBackClick = { navController.popBackStack() },
                        notifications = listOf()
                    )
                }
                composable(NavRoute.TipsBelajar.route) {
                    TipsScreen(
                        onBackClick = { navController.popBackStack() },
                        navController = navController
                    )
                }
                composable(NavRoute.Dictionary.route) {
                    DictionaryScreen(navController = navController)
                }
                composable(NavRoute.BISINDO.route) {
                    BISINDOScreen(navController = navController)
                }
                composable(NavRoute.SIBI.route) {
                    SIBIScreen(navController = navController)
                }
                composable(NavRoute.Translate.route) {
                    TranslateScreen(navController = navController)
                }
                composable(NavRoute.TranslateGuide.route) {
                    TranslateGuideScreen(
                        onBackClick = { navController.popBackStack() },
                        navController = navController
                    )
                }
                composable(NavRoute.History.route) {
                    HistoryScreen(navController = navController, historyItems = listOf())
                }
                composable(NavRoute.Settings.route) {
                    SettingsScreen(navController = navController)
                }
                composable(NavRoute.EditAccount.route) {
                    EditAccountScreen(
                        navController = navController,
                        onSaveClick = { name, phone, newPassword -> }
                    )
                }
                composable(NavRoute.LanguageSettings.route) {
                    LanguageSettingsScreen(
                        navController = navController,
                        currentLanguage = "Bahasa Indonesia",
                        onLanguageChange = { }
                    )
                }
                composable(NavRoute.ThemeSettings.route) {
                    ThemeSettingsScreen(
                        navController,
                        "System Default",
                        onThemeChange = {}
                    )
                }
                composable(NavRoute.About.route) {
                    AboutIsyaraScreen(
                        onBackClick = { navController.popBackStack() },
                        navController = navController
                    )
                }
                // Feedback screen
                composable(NavRoute.Feedback.route) {
                    // Memanggil FeedbackScreen dengan viewModel
                    FeedbackScreen(
                        navController = navController,
                        authViewModel = hiltViewModel() // Menggunakan Hilt untuk menyuntikkan AuthViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CustomBottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        val items = listOf(
            NavigationItem("Dashboard", Icons.Filled.Home, NavRoute.Dashboard.route),
            NavigationItem("Dictionary", Icons.Filled.Book, NavRoute.Dictionary.route),
            NavigationItem("Translate", Icons.Filled.Translate, NavRoute.Translate.route),
            NavigationItem("History", Icons.Filled.History, NavRoute.History.route),
            NavigationItem("Settings", Icons.Filled.Settings, NavRoute.Settings.route)
        )

        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(NavRoute.Dashboard.route) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.scale(if (currentRoute == item.route) 1.2f else 1f),
                        tint = if (currentRoute == item.route) Color.Blue else Color.Black
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                        color = if (currentRoute == item.route) Color.Blue else Color.Black
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}
