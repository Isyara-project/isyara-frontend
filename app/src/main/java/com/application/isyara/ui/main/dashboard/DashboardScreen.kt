package com.application.isyara.ui.main.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.isyara.R
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.state.Result
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.main.QuickAccessItem
import com.application.isyara.viewmodel.auth.AuthViewModel
import com.application.isyara.viewmodel.main.ProfileViewModel
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun DashboardScreen(
    onSearchClick: () -> Unit,
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val scrollState = rememberScrollState()
    val profileState by profileViewModel.profileState.collectAsState()
    val scrollThreshold = 50

    val isScrolled by remember {
        derivedStateOf { scrollState.value > scrollThreshold }
    }
    val headerBackgroundColor by animateColorAsState(
        targetValue = if (isScrolled) Color.White else Color.Transparent,
        animationSpec = tween(durationMillis = 500),
        label = "colorAnimation"
    )
    var isProfileCardClicked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.fetchProfile()
    }

    val fullName = when (profileState) {
        is Result.Idle -> null
        is Result.Loading -> null
        is Result.Success -> (profileState as Result.Success).data.fullname
        is Result.Error -> null
    }

    val logoColor by animateColorAsState(
        targetValue = if (isScrolled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
        animationSpec = tween(durationMillis = 500), label = ""
    )


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = when (profileState) {
                    is Result.Idle -> "Memuat profil data..."
                    is Result.Loading -> "Memuat profil data..."
                    is Result.Success -> "Selamat Datang, $fullName!"
                    is Result.Error -> "Gagal memuat profil"
                },
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
            QuickAccessCard(
                navController = navController
            )

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
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
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
                .zIndex(2f),
            logoColor = logoColor
        )

        if (isProfileCardClicked) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .offset(y = 50.dp)
            ) {
                ProfileCardSmall(navController = navController, fullName = fullName ?: "User")
            }
        }
    }
}

@Composable
fun ProfileCardSmall(
    navController: NavController,
    fullName: String,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val cardWidth = 230.dp
    val cardHeight = 250.dp
    var showLogoutDialog by remember { mutableStateOf(false) }

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
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(Color(0xFFE1F5FE), CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(NavRoute.EditAccount.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Foto Profil",
                        modifier = Modifier.size(60.dp),
                        tint = Color(0xFF008E9B)
                    )
                }
            }

            Text(
                text = fullName,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { showLogoutDialog = true },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(36.dp)
                    .fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if (showLogoutDialog) {
                LogoutConfirmationDialog(
                    onConfirm = {
                        viewModel.logout()
                        navController.navigate(NavRoute.Onboarding.route) {
                            popUpTo(NavRoute.Settings.route) { inclusive = true }
                        }
                        showLogoutDialog = false
                    },
                    onDismiss = {
                        showLogoutDialog = false
                    }
                )
            }
        }
    }
}


@Composable
fun LogoutConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Konfirmasi Logout") },
        text = { Text("Apakah Anda yakin ingin keluar?") },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Ya")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Batal")
            }
        }
    )
}


@Composable
fun HotNewsCarousel() {
    val newsItems = listOf(
        "",
        "",
        ""
    )

    val imageResources = listOf(
        R.drawable.hot_news_1,
        R.drawable.hot_news_2,
        R.drawable.hot_news_3
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    var offsetX by remember { mutableFloatStateOf(0f) }

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
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResources[currentIndex]),
                contentDescription = "Hot News Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            ) {
                Text(
                    text = newsItems[currentIndex],
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
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
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .padding(16.dp),
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
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = title,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun QuickAccessCard(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val columns = when {
        screenWidth > 600 -> 5
        screenWidth > 400 -> 4
        else -> 3
    }

    val quickAccessItems = listOf(
        QuickAccessItem(
            imageVector = Icons.Default.Translate,
            label = "Translate",
            backgroundColor = Color(0xFF1E88E5),
            contentDescription = "Terjemahkan teks"
        ),
        QuickAccessItem(
            imageVector = Icons.Default.TipsAndUpdates,
            label = "Tips Belajar",
            backgroundColor = Color(0xFFFB8C00),
            contentDescription = "Tips dan Pembaruan"
        ),
        QuickAccessItem(
            painter = painterResource(id = R.drawable.ic_sibi_huruf),
            label = "Sibi Gambar",
            backgroundColor = Color(0xFF587FC0),
            contentDescription = "Gambar SIBI"
        ),
        QuickAccessItem(
            painter = painterResource(id = R.drawable.ic_sibi),
            label = "Sibi Video",
            backgroundColor = Color(0xFF5387E1),
            contentDescription = "Video SIBI"
        ),
        QuickAccessItem(
            imageVector = Icons.Default.MoreHoriz,
            label = "Lainnya",
            backgroundColor = Color(0xFF757575),
            contentDescription = "Opsi Lainnya"
        )
    )

    var showMoreDialog by remember { mutableStateOf(false) }

    if (showMoreDialog) {
        AlertDialog(
            onDismissRequest = { showMoreDialog = false },
            title = { Text("Lainnya") },
            text = {
                Column {
                    TextButton(onClick = {
                        showMoreDialog = false
                        navController.navigate(NavRoute.Settings.route)
                    }) {
                        Text("Settings")
                    }
                    TextButton(onClick = {
                        showMoreDialog = false
                    }) {
                        Text("Help & Support")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showMoreDialog = false }) {
                    Text("Tutup")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .heightIn(min = 120.dp, max = 200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(quickAccessItems) { item ->
                QuickAccessIcon(
                    imageVector = item.imageVector,
                    painter = item.painter,
                    label = item.label,
                    backgroundColor = item.backgroundColor,
                    contentDescription = item.contentDescription,
                    onClick = {
                        when (item.label) {
                            "Translate" -> navController.navigate(NavRoute.Translate.route)
                            "Tips Belajar" -> navController.navigate(NavRoute.TipsBelajar.route)
                            "Sibi Gambar" -> navController.navigate(NavRoute.SibiPicture.route)
                            "Sibi Video" -> navController.navigate(NavRoute.SibiVideo.route)
                            "Lainnya" -> showMoreDialog = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun QuickAccessIcon(
    imageVector: ImageVector?,
    painter: Painter?,
    label: String,
    backgroundColor: Color,
    contentDescription: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(backgroundColor, CircleShape)
                .clickable(onClick = onClick)
                .shadow(4.dp, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            when {
                imageVector != null -> {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = contentDescription,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                painter != null -> {
                    Image(
                        painter = painter,
                        contentDescription = contentDescription,
                        modifier = Modifier.size(30.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                else -> {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "Placeholder",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(80.dp)
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

