package com.application.isyara.ui.main.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.main.QuickAccessItem
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


@Composable
fun DashboardScreen(
    userName: String = "Zacky",
    fullName: String = "Nama Lengkap",
    onSearchClick: () -> Unit,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val scrollState = rememberScrollState()

    val scrollThreshold = 50
    val isScrolled by remember {
        derivedStateOf { scrollState.value > scrollThreshold }
    }

    val headerBackgroundColor by animateColorAsState(
        targetValue = if (isScrolled) Color.White else Color.Transparent,
        animationSpec = tween(durationMillis = 1000)
    )

    var isProfileCardClicked by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Selamat Datang, $userName!",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Hot News Carousel
            HotNewsCarousel()

            Spacer(modifier = Modifier.height(16.dp))

            // Usage Statistics
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                UsageStatistics()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quick Access Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                QuickAccessCard(
                    screenWidth = screenWidth,
                    navController = navController
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            DevelopmentSection()

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Background Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .offset { IntOffset(x = 0, y = -scrollState.value) }
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
                    )
                )
                .zIndex(-1f)
        )

        // Header
        AppHeaderMain(
            title = "Isyara",
            backgroundColor = Brush.verticalGradient(
                colors = listOf(headerBackgroundColor, headerBackgroundColor)
            ),
            showDashboardElements = true,
            onSearchClick = { navController.navigate(NavRoute.Search.route) },
            onNotificationClick = { navController.navigate(NavRoute.Notification.route) },
            onProfileClick = { isProfileCardClicked = !isProfileCardClicked },
            paddingStart = 16.dp,
            paddingEnd = 16.dp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .zIndex(2f)
        )

        if (isProfileCardClicked) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .offset(y = 50.dp)
            ) {
                ProfileCardSmall()
            }
        }
    }
}


@Composable
fun ProfileCardSmall() {
    val cardWidth = 230.dp
    val cardHeight = 250.dp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFf0f0f0))
            .zIndex(3f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Foto Profil
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE1F5FE))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Foto Profil Kecil",
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFF008E9B)
                )
            }

            // Username
            Text(
                text = "Username",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            // Email
            Text(
                text = "lucubanget@gmail.com",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(40.dp)
                    .fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(Color(0xFF008E9B))
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


@Composable
fun HotNewsCarousel() {
    val newsItems = listOf(
        "Hot News: Pelatihan Bahasa Isyarat Gratis",
        "Hot News: Fitur Baru di Isyara",
        "Hot News: Komunitas Tuna Rungu Berkembang"
    )

    var currentIndex by remember { mutableStateOf(0) }
    var offsetX by remember { mutableStateOf(0f) }

    LaunchedEffect(currentIndex) {
        delay(5000)
        offsetX = 0f
        currentIndex = (currentIndex + 1) % newsItems.size
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .background(Color(0xFFFFCCCB), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = newsItems[currentIndex],
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(newsItems.size) { index ->
                val color = if (index == currentIndex) Color(0xFF008E9B) else Color.Gray
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color, CircleShape)
                )
            }
        }
    }
}


@Composable
fun UsageStatistics() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F1F1))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatisticItem("Translate", "10 kata")
            StatisticItem("Kamus", "5 kata")
            StatisticItem("Waktu", "30 menit")
        }
    }
}

@Composable
fun StatisticItem(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun QuickAccessCard(screenWidth: Int, navController: NavController) {
    val columns = if (screenWidth > 400) 4 else 3

    val quickAccessItems = listOf(
        QuickAccessItem(Icons.Default.Translate, "Translate"),
        QuickAccessItem(Icons.Default.Book, "Dictionary"),
        QuickAccessItem(Icons.Default.TipsAndUpdates, "Tips Belajar"),
        QuickAccessItem(Icons.Default.Language, "SIBI"),
        QuickAccessItem(Icons.Default.Translate, "BISINDO"),
        QuickAccessItem(Icons.Default.MoreHoriz, "Lainnya")
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F1F1))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = 200.dp, max = 350.dp)
        ) {
            items(quickAccessItems) { item ->
                QuickAccessIcon(
                    icon = item.icon,
                    label = item.label,
                    onClick = {
                        when (item.label) {
                            "Translate" -> {
                                navController.navigate(NavRoute.Translate.route)
                            }

                            "Dictionary" -> {
                                navController.navigate(NavRoute.Dictionary.route)
                            }

                            "Tips Belajar" -> {
                                navController.navigate(NavRoute.TipsBelajar.route)
                            }

                            "SIBI" -> {
                                navController.navigate(NavRoute.SIBI.route)
                            }

                            "BISINDO" -> {
                                navController.navigate(NavRoute.BISINDO.route)
                            }

                            "Lainnya" -> {}
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun QuickAccessIcon(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color(0xFFFF5733), CircleShape)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}


@Composable
fun DevelopmentSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pengembangan Lebih Lanjut",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val navController = rememberNavController()
    DashboardScreen(
        userName = "Zacky",
        onSearchClick = {},
        navController = navController
    )
}
