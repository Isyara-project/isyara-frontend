package com.application.isyara.ui.main.translate

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraFront
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.state.Result
import com.application.isyara.utils.translate.CameraPreview
import com.application.isyara.viewmodel.translate.TranslateViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun TranslateScreen(
    navController: NavController,
    viewModel: TranslateViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val predictionState by viewModel.predictionState.collectAsState()
    val isTranslationActive by viewModel.isTranslationActive.collectAsState()
    val isDictionaryLoaded by viewModel.isDictionaryLoaded.collectAsState()

    val isFrontCamera = remember { mutableStateOf(true) }
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasCameraPermission = isGranted
            if (!isGranted) {
                Timber.d("TranslateScreen: Camera permission denied")
                Toast.makeText(context, "Kamera tidak diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Timber.d("TranslateScreen: Camera permission granted")
                Toast.makeText(context, "Kamera diizinkan", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // Meminta izin CAMERA jika belum diberikan
    LaunchedEffect(key1 = Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // Menampilkan pesan error jika ada
    if (predictionState is Result.Error && (predictionState as Result.Error).message.isNotBlank()) {
        LaunchedEffect(predictionState) {
            Timber.e("TranslateScreen: Menampilkan error toast: ${(predictionState as Result.Error).message}")
            Toast.makeText(
                context,
                "Error: ${(predictionState as Result.Error).message}",
                Toast.LENGTH_LONG
            ).show()
            viewModel.resetTranslation()
        }
    }

    // Menambahkan log untuk debugging
    LaunchedEffect(predictionState) {
        Timber.d("TranslateScreen: predictionState updated: $predictionState")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {

        // Header
        AppHeaderMain(
            title = "Translate Bahasa Isyarat",
            onHelpClick = { },
            trailingContent = {
                IconButton(onClick = { navController.navigate(NavRoute.History.route) }) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "History",
                        tint = Color.White
                    )
                }
            },
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

        if (hasCameraPermission) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent, RoundedCornerShape(12.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                CameraPreview(
                    context = context,
                    viewModel = viewModel,
                    isFrontCamera = isFrontCamera.value,
                    isTranslationActive = isTranslationActive,
                    modifier = Modifier.fillMaxSize()
                )

                // Teks Instruksi dengan Latar Belakang Semi-Transparent
                if (!isTranslationActive) {
                    Text(
                        text = "Arahkan tangan Anda ke area ini",
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Icon untuk Mengganti Kamera dengan Efek Visual
                Icon(
                    imageVector = Icons.Default.CameraFront,
                    contentDescription = "Switch Camera",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clickable {
                            coroutineScope.launch {
                                Timber.d("TranslateScreen: Switch camera clicked")
                                isFrontCamera.value = !isFrontCamera.value
                                if (isFrontCamera.value) {
                                    Toast
                                        .makeText(context, "Kamera Depan", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast
                                        .makeText(context, "Kamera Belakang", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    val scrollState = rememberScrollState()
                    LaunchedEffect(predictionState) {
                        if (predictionState is Result.Success) {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .horizontalScroll(scrollState)
                    ) {
                        Text(
                            text = when (predictionState) {
                                is Result.Success -> (predictionState as Result.Success<String>).data
                                is Result.Loading -> "Memproses terjemahan..."
                                is Result.Error -> "Terjadi kesalahan: ${(predictionState as Result.Error).message}"
                                else -> "Menunggu hasil terjemahan"
                            },
                            fontSize = 18.sp,
                            color = when (predictionState) {
                                is Result.Success -> Color.Black
                                is Result.Error -> Color.Red
                                else -> Color.Gray
                            },
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Translate (Toggle Start/Stop)
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF008E9B), CircleShape)
                        .clickable(
                            enabled = true,
                            onClick = {
                                coroutineScope.launch {
                                    if (!isTranslationActive) {
                                        Timber.d("TranslateScreen: Starting translation via button")
                                        viewModel.setTranslationActive(true)
                                        Toast
                                            .makeText(
                                                context,
                                                "Terjemahan Dimulai",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    } else {
                                        Timber.d("TranslateScreen: Stopping translation via button")
                                        viewModel.setTranslationActive(false)
                                        Toast
                                            .makeText(
                                                context,
                                                "Terjemahan Dihentikan",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                            },
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isTranslationActive) Icons.Default.Stop else Icons.Default.PlayArrow,
                        contentDescription = if (isTranslationActive) "Stop Translate" else "Start Translate",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isTranslationActive && !isDictionaryLoaded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}
