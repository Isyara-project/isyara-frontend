@file:OptIn(FlowPreview::class)

package com.application.isyara.utils.dictionary

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.navigation.NavRoute
import com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel
import kotlinx.coroutines.FlowPreview

@Composable
fun VideoItem(
    video: VideoItems,
    navController: NavController,
    viewModel: DictionaryVideoViewModel,
    isDownloading: Boolean,
    onDownloadClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    val thumbnail = viewModel.getThumbnail(video.url)
    var hasError by remember { mutableStateOf(false) }

    if (thumbnail == null && !hasError) {
        LaunchedEffect(video.url) {
            viewModel.loadThumbnail(video)
        }
    }

    LaunchedEffect(thumbnail) {
        if (thumbnail == null && video.isDownloaded) {
            hasError = true
        }
    }

    val isLoading = thumbnail == null && !hasError

    if (isLoading) {
        ShimmerPlaceholderCard()
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        hasError -> {
                            Icon(
                                imageVector = Icons.Default.EmojiPeople,
                                contentDescription = "Error Loading Thumbnail",
                                tint = Color.Red,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        thumbnail != null -> {
                            val alphaAnim by animateFloatAsState(
                                targetValue = 1f,
                                animationSpec = tween(
                                    durationMillis = 500,
                                    easing = FastOutSlowInEasing
                                ), label = ""
                            )

                            Image(
                                bitmap = thumbnail,
                                contentDescription = "Video Thumbnail",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .alpha(alphaAnim),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Informasi Video
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = video.title.capitalizeWords(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Isyarat untuk ${video.title.capitalizeWords()}",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                // Tombol Play dan Download/Delete
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Tombol Play
                    IconButton(
                        onClick = {
                            if (video.isDownloaded) {
                                navController.navigate(NavRoute.VideoPlayer.createRoute(video.url))
                            } else {
                                navController.navigate(NavRoute.VideoPlayer.createRoute(video.url))
                            }
                        },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play Video",
                            tint = Color(0xFF008E9B),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tombol Download atau Delete
                    IconButton(
                        onClick = {
                            if (!video.isDownloaded && !isDownloading) {
                                onDownloadClick(video.title)
                            } else if (video.isDownloaded) {
                                onDeleteClick(video.url)
                            }
                        },
                        modifier = Modifier.size(28.dp)
                    ) {
                        when {
                            isDownloading -> {
                                ShimmerPlaceholderButton()
                            }

                            video.isDownloaded -> {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Downloaded",
                                    tint = Color.Green,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            else -> {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = "Download Video",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerPlaceholderButton() {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.LightGray.copy(alpha = 0.3f),
                        Color.LightGray.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
    )
}


