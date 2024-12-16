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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.application.isyara.R
import com.application.isyara.data.model.ProfileData
import com.application.isyara.navigation.NavRoute
import com.application.isyara.ui.viewmodel.UsageStatisticViewModel
import com.application.isyara.utils.state.Result
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.main.QuickAccessItem
import com.application.isyara.viewmodel.auth.AuthViewModel
import com.application.isyara.viewmodel.dashboard.ProfileViewModel
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun DashboardScreen(
    onSearchClick: () -> Unit,
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val profileState by profileViewModel.profileState.collectAsState()
    val scrollThreshold = 50

    val isScrolled by remember {
        derivedStateOf { scrollState.value > scrollThreshold }
    }
    val headerBackgroundColor by animateColorAsState(
        targetValue = if (isScrolled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface.copy(
            alpha = 0f
        ),
        animationSpec = tween(durationMillis = 500),
        label = "colorAnimation"
    )
    var isProfileCardClicked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.fetchProfile()
    }

    val fullName = when (profileState) {
        is Result.Idle, is Result.Loading -> stringResource(R.string.loading_profile_data)
        is Result.Success -> (profileState as Result.Success).data.fullname
        is Result.Error -> stringResource(R.string.failed_loading_profile)
    }

    val pictureUrl = when (profileState) {
        is Result.Success -> {
            val originalUrl = (profileState as Result.Success<ProfileData>).data.picture
            originalUrl?.replace(
                "https://storage.cloud.google.com/",
                "https://storage.googleapis.com/"
            )?.let {
                "$it?timestamp=${System.currentTimeMillis()}"
            }
        }

        else -> null
    }

    val logoColor by animateColorAsState(
        targetValue = if (isScrolled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
        animationSpec = tween(durationMillis = 500),
        label = ""
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
                    is Result.Idle, is Result.Loading -> context.getString(R.string.loading_profile_data)
                    is Result.Success -> stringResource(R.string.welcome, fullName)
                    is Result.Error -> context.getString(R.string.failed_loading_profile)
                },
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(36.dp))

            HotNewsCarousel()

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                UsageStatistics()
            }

            Spacer(modifier = Modifier.height(16.dp))

            QuickAccessCard(
                navController = navController
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

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
                ProfileCardSmall(
                    navController = navController,
                    fullName = fullName,
                    pictureUrl = pictureUrl
                )
            }
        }
    }
}


@Composable
fun ProfileCardSmall(
    navController: NavController,
    fullName: String,
    pictureUrl: String?,
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
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
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
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .shadow(4.dp, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (!pictureUrl.isNullOrEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pictureUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Foto Profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        placeholder = painterResource(R.drawable.ic_image_placeholder),
                        error = painterResource(R.drawable.ic_error)
                    )
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate(NavRoute.EditAccount.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Foto Profil",
                            modifier = Modifier.size(60.dp),
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }

            Text(
                text = fullName,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Button(
                onClick = { showLogoutDialog = true },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(28.dp)
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Logout",
                    color = MaterialTheme.colorScheme.onPrimary,
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
        title = { Text(stringResource(R.string.confirm_logout)) },
        text = { Text(stringResource(R.string.sure_logout)) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.no))
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
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.tertiary),
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
                    .background(MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f))
            ) {
                Text(
                    text = newsItems[currentIndex],
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary,
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
                val color =
                    if (index == currentIndex) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
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
fun UsageStatistics(
    usageStatisticViewModel: UsageStatisticViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val totalCharacters by usageStatisticViewModel.totalCharacters.collectAsState()
    val totalDownloadedDictionaries by usageStatisticViewModel.totalDownloadedDictionaries.collectAsState()
    val totalDownloadedPictureDictionaries by usageStatisticViewModel.totalDownloadedPictureDictionaries.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatisticItem(
                stringResource(R.string.translate),
                if (totalCharacters >= 0) stringResource(
                    R.string.letter,
                    totalCharacters
                ) else stringResource(
                    R.string.loading
                )
            )
            StatisticItem(
                stringResource(R.string.dictionary_video),
                if (totalDownloadedDictionaries >= 0) stringResource(
                    R.string.download,
                    totalDownloadedDictionaries
                ) else context.getString(R.string.loading)
            )
            StatisticItem(
                stringResource(R.string.dictionary_picture),
                if (totalDownloadedDictionaries >= 0) stringResource(
                    R.string.download,
                    totalDownloadedPictureDictionaries
                ) else context.getString(R.string.loading)
            )
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
    val context = LocalContext.current
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
            label = stringResource(R.string.translate),
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(R.string.text_translate)
        ),

        QuickAccessItem(
            painter = painterResource(id = R.drawable.ic_sibi_huruf),
            label = stringResource(R.string.sibi_picture),
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            contentDescription = stringResource(R.string.sibi_picture)
        ),
        QuickAccessItem(
            painter = painterResource(id = R.drawable.ic_sibi),
            label = stringResource(R.string.sibi_video),
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(R.string.sibi_video)
        ),
        QuickAccessItem(
            imageVector = Icons.Default.TipsAndUpdates,
            label = stringResource(R.string.tips_study),
            backgroundColor = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(R.string.tips_isyarat)
        ),
        QuickAccessItem(
            imageVector = Icons.Default.MoreHoriz,
            label = stringResource(R.string.another),
            backgroundColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            contentDescription = stringResource(R.string.another)
        )
    )

    var showMoreDialog by remember { mutableStateOf(false) }

    if (showMoreDialog) {
        AlertDialog(
            onDismissRequest = { showMoreDialog = false },
            title = { Text(stringResource(R.string.another)) },
            text = {
                Column {
                    TextButton(onClick = {
                        showMoreDialog = false
                        navController.navigate(NavRoute.Settings.route)
                    }) {
                        Text(stringResource(R.string.settings))
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showMoreDialog = false }) {
                    Text(stringResource(R.string.close))
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .heightIn(min = 120.dp, max = 200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                            context.getString(R.string.translate) -> navController.navigate(NavRoute.Translate.route)
                            context.getString(R.string.tips_study) -> navController.navigate(
                                NavRoute.TipsBelajar.route
                            )

                            context.getString(R.string.sibi_picture) -> navController.navigate(
                                NavRoute.SibiPicture.route
                            )

                            context.getString(R.string.sibi_video) -> navController.navigate(
                                NavRoute.SibiVideo.route
                            )

                            context.getString(R.string.another) -> showMoreDialog = true
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
                        tint = MaterialTheme.colorScheme.onTertiary,
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
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
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