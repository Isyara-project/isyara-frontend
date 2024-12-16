package com.application.isyara.ui.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.application.isyara.R
import com.application.isyara.data.model.ProfileData
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.auth.AuthViewModel
import com.application.isyara.viewmodel.dashboard.ProfileViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        AppHeaderMain(
            title = stringResource(R.string.settings),
            onBackClick = { navController.popBackStack() },
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            ),
            showDashboardElements = false,
            isTopLevelPage = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileSection(viewModel = hiltViewModel()) {
            navController.navigate(NavRoute.EditAccount.route)
        }

        SettingsOption(
            title = "Ubah Password",
            icon = Icons.Default.Password,
            onClick = { navController.navigate(NavRoute.ChangePassword.route) }
        )

        SettingsOption(
            title = stringResource(R.string.application_language),
            icon = Icons.Default.Language,
            onClick = { navController.navigate(NavRoute.LanguageSettings.route) }
        )

        SettingsOption(
            title = stringResource(R.string.application_theme),
            icon = Icons.Default.ModeNight,
            onClick = { navController.navigate(NavRoute.ThemeSettings.route) }
        )

        SettingsOption(
            title = stringResource(R.string.about_isyara),
            icon = Icons.Default.Info,
            onClick = { navController.navigate(NavRoute.About.route) }
        )

        SettingsOption(
            title = stringResource(R.string.feedback_user),
            icon = Icons.Default.Feedback,
            onClick = { navController.navigate(NavRoute.Feedback.route) }
        )

        SettingsOption(
            title = stringResource(R.string.logout),
            icon = Icons.Default.Logout,
            onClick = { showLogoutDialog = true },
            isDestructive = true
        )
    }
    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onConfirm = {
                authViewModel.logout()
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


@Composable
fun ProfileSection(
    viewModel: ProfileViewModel = hiltViewModel(),
    onEditAccountClick: () -> Unit
) {
    val profileState by viewModel.profileState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFE1F5FE), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            when (profileState) {
                is Result.Success -> {
                    val profileData = (profileState as Result.Success<ProfileData>).data
                    if (!profileData.picture.isNullOrEmpty()) {
                        val correctedPictureUrl = profileData.picture.replace(
                            "https://storage.cloud.google.com/",
                            "https://storage.googleapis.com/"
                        )
                        val pictureUrlWithTimestamp =
                            "$correctedPictureUrl?timestamp=${System.currentTimeMillis()}"

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pictureUrlWithTimestamp)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Foto Profil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFF008E9B), CircleShape),
                            placeholder = painterResource(R.drawable.ic_image_placeholder),
                            error = painterResource(R.drawable.ic_error)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Foto Profil",
                            modifier = Modifier.size(64.dp),
                            tint = Color(0xFF008E9B)
                        )
                    }
                }

                is Result.Loading -> {
                    CircularProgressIndicator(
                        color = Color(0xFF008E9B),
                        modifier = Modifier.size(32.dp)
                    )
                }

                is Result.Error -> {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Foto Profil",
                        modifier = Modifier.size(64.dp),
                        tint = Color(0xFF008E9B)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.failed_loading_profile),
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                else -> {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Foto Profil",
                        modifier = Modifier.size(64.dp),
                        tint = Color(0xFF008E9B)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = when (profileState) {
                is Result.Success -> (profileState as Result.Success<ProfileData>).data.fullname
                else -> "Nama Pengguna"
            },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEditAccountClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
        ) {
            Text(
                text = stringResource(R.string.edit_foto_profile),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}


@Composable
fun SettingsOption(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isDestructive) Color.Red else Color(0xFF008E9B),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDestructive) Color.Red else Color.Black
            )
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
                Text(stringResource(R.string.cancel))
            }
        }
    )
}
