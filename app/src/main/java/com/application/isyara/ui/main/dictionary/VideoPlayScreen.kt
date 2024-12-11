package com.application.isyara.ui.main.dictionary

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.application.isyara.utils.dictionary.NetworkUtils
import com.application.isyara.utils.dictionary.capitalizeWords
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.viewmodel.main.DictionaryViewModel
import kotlinx.coroutines.FlowPreview
import timber.log.Timber
import androidx.compose.ui.graphics.Color as ComposeColor

@OptIn(UnstableApi::class)
@FlowPreview
@Composable
fun VideoPlayerScreen(
    navController: NavController,
    videoUrl: String,
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var localPath by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(videoUrl) {
        localPath = viewModel.getLocalPath(videoUrl)
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = ComposeColor(0xFF008E9B))
        }
    } else {
        val videoSource = if (localPath != null) {
            "file://${localPath}"
        } else {
            videoUrl
        }

        val hasInternet = NetworkUtils.isNetworkAvailable(context)
        val finalVideoSource = if (videoSource.startsWith("http") && !hasInternet) {
            null
        } else {
            videoSource
        }

        if (finalVideoSource == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak dapat memutar video tanpa koneksi internet atau video belum diunduh.",
                    color = Color.Red,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            val exoPlayer = remember {
                ExoPlayer.Builder(context)
                    .setBandwidthMeter(DefaultBandwidthMeter.Builder(context).build())
                    .build().apply {
                        setMediaItem(MediaItem.fromUri(Uri.parse(finalVideoSource)))
                        prepare()
                        playWhenReady = true
                        Timber.d("ExoPlayer initialized with $finalVideoSource")
                    }
            }

            DisposableEffect(key1 = exoPlayer) {
                onDispose {
                    exoPlayer.release()
                    Timber.d("ExoPlayer released")
                }
            }

            DisposableEffect(exoPlayer) {
                val listener = object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        super.onPlaybackStateChanged(state)
                        when (state) {
                            Player.STATE_BUFFERING -> Timber.d("Buffering")
                            Player.STATE_READY -> Timber.d("Ready to play")
                            Player.STATE_ENDED -> {
                                Timber.d("Playback ended")
                                navController.popBackStack()
                            }

                            Player.STATE_IDLE -> Timber.d("Player idle")
                        }
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        super.onPlayerError(error)
                        Timber.e("Player error: ${error.message}")
                    }
                }
                exoPlayer.addListener(listener)
                onDispose {
                    exoPlayer.removeListener(listener)
                }
            }

            val videoTitle = if (videoUrl.isNotEmpty()) {
                videoUrl.substringAfterLast("/").substringBeforeLast(".mp4").replace("-", " ")
                    .capitalizeWords()
            } else {
                ""
            }

            Column(modifier = Modifier.fillMaxSize()) {
                // AppHeaderMain
                AppHeaderMain(
                    title = videoTitle,
                    backgroundColor = Brush.horizontalGradient(
                        colors = listOf(ComposeColor(0xFF008E9B), ComposeColor(0xFF00B4D8))
                    ),
                    onBackClick = { navController.popBackStack() },
                    isTopLevelPage = false
                )

                // Video Player
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ComposeColor.Black)
                ) {
                    AndroidView(
                        factory = {
                            PlayerView(context).apply {
                                player = exoPlayer
                                useController = true
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    // Overlay gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        ComposeColor.Transparent,
                                        ComposeColor.Black.copy(alpha = 0.7f)
                                    ),
                                    startY = 300f
                                )
                            )
                    )
                }
            }
        }
    }
}