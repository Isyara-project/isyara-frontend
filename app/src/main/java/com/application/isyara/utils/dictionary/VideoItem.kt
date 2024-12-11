package com.application.isyara.utils.dictionary

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.navigation.NavRoute
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun VideoItem(
    video: VideoItems,
    navController: NavController,
    thumbnailCache: MutableMap<String, ImageBitmap?>,
    isDownloading: Boolean,
    onDownloadClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    val videoUrl = video.url
    val videoTitle = video.title
    var thumbnail by remember { mutableStateOf(thumbnailCache[videoUrl]) }
    var isLoading by remember { mutableStateOf(thumbnail == null) }
    var hasError by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    if (thumbnail == null && isLoading) {
        LaunchedEffect(videoUrl) {
            coroutineScope.launch {
                val extractedThumbnail = if (video.isDownloaded && video.localPath != null) {
                    extractThumbnail(video.localPath)
                } else {
                    extractThumbnailFromUrl(videoUrl)
                }
                if (extractedThumbnail != null) {
                    thumbnailCache[videoUrl] = extractedThumbnail
                    thumbnail = extractedThumbnail
                    isLoading = false
                    Timber.d("Thumbnail extracted for $videoUrl")
                } else {
                    hasError = true
                    isLoading = false
                    Timber.e("Failed to extract thumbnail for $videoUrl")
                }
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail Video
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    hasError -> {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Error Loading Thumbnail",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    thumbnail != null -> {
                        // Animasi Fade-In
                        val alphaAnim by animateFloatAsState(
                            targetValue = 1f,
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            ), label = ""
                        )

                        Image(
                            bitmap = thumbnail!!,
                            contentDescription = "Video Thumbnail",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .alpha(alphaAnim),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Informasi Video
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = videoTitle.capitalizeWords(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Isyarat untuk $videoTitle",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Tombol Play, Download, dan Delete
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Tombol Play
                IconButton(onClick = {
                    if (video.isDownloaded) {
                        // Play dari penyimpanan lokal
                        navController.navigate(NavRoute.VideoPlayer.createRoute(videoUrl))
                    } else {
                        // Play dari URL (memerlukan koneksi internet)
                        navController.navigate(NavRoute.VideoPlayer.createRoute(videoUrl))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Video",
                        tint = Color(0xFF008E9B)
                    )
                }

                IconButton(onClick = {
                    if (!video.isDownloaded && !isDownloading) {
                        onDownloadClick(videoTitle)
                    } else if (video.isDownloaded) {
                        onDeleteClick(videoUrl)
                    }
                }) {
                    when {
                        isDownloading -> {
                            CircularProgressIndicator(
                                color = Color.Gray,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        }

                        video.isDownloaded -> {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Downloaded",
                                tint = Color.Green
                            )
                        }

                        else -> {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = "Download Video",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
