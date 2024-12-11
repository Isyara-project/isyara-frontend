package com.application.isyara.ui.main.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.application.isyara.R
import com.application.isyara.utils.history.HistoryItem
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.viewmodel.history.HistoryViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val historyItems by viewModel.historyItems.collectAsState()
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<HistoryItem?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Histori Aktivitas",
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF008E9B),
                    Color(0xFF00B4D8)
                )
            ),
            trailingContent = {
                IconButton(onClick = { showDeleteConfirm = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus Semua",
                        tint = Color.White
                    )
                }
            },
            showDashboardElements = false,
            isTopLevelPage = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Konten Daftar Riwayat
        if (historyItems.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(historyItems, key = {
                    when (it) {
                        is HistoryItem.Translation -> "translation_${it.id}"
                        is HistoryItem.DownloadedDictionary -> "dictionary_${it.url}"
                    }
                }) { item ->
                    HistoryCard(item, onDelete = {
                        itemToDelete = item
                        showDeleteConfirm = true
                    })
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "No History",
                        tint = Color.Gray,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Belum ada aktivitas.\nJelajahi fitur Isyara sekarang!",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    if (showDeleteConfirm) {
        if (itemToDelete != null) {
            when (itemToDelete) {
                is HistoryItem.Translation -> {
                    AlertDialog(
                        onDismissRequest = {
                            showDeleteConfirm = false
                            itemToDelete = null
                        },
                        title = { Text(text = "Konfirmasi Penghapusan") },
                        text = { Text(text = "Apakah Anda yakin ingin menghapus riwayat terjemahan ini?") },
                        confirmButton = {
                            TextButton(onClick = {
                                (itemToDelete as HistoryItem.Translation).let { translation ->
                                    viewModel.deleteTranslatedTextById(translation.id)
                                }
                                showDeleteConfirm = false
                                itemToDelete = null
                            }) {
                                Text("Ya")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDeleteConfirm = false
                                itemToDelete = null
                            }) {
                                Text("Tidak")
                            }
                        }
                    )
                }

                is HistoryItem.DownloadedDictionary -> {
                    // Konfirmasi Hapus Selektif untuk DownloadedDictionary
                    AlertDialog(
                        onDismissRequest = {
                            showDeleteConfirm = false
                            itemToDelete = null
                        },
                        title = { Text(text = "Konfirmasi Penghapusan") },
                        text = { Text(text = "Apakah Anda yakin ingin menghapus item kamus yang diunduh ini?") },
                        confirmButton = {
                            TextButton(onClick = {
                                (itemToDelete as HistoryItem.DownloadedDictionary).let { dict ->
                                    viewModel.deleteDownloadedDictionaryItemByUrl(dict.url)
                                }
                                showDeleteConfirm = false
                                itemToDelete = null
                            }) {
                                Text("Ya")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDeleteConfirm = false
                                itemToDelete = null
                            }) {
                                Text("Tidak")
                            }
                        }
                    )
                }

                else -> {}
            }
        } else {
            // Konfirmasi Hapus Semua
            AlertDialog(
                onDismissRequest = { showDeleteConfirm = false },
                title = { Text(text = "Konfirmasi Penghapusan") },
                text = { Text(text = "Apakah Anda yakin ingin menghapus semua riwayat terjemahan dan kamus yang diunduh?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteAllTranslatedTexts()
                        viewModel.deleteAllDownloadedDictionaries()
                        showDeleteConfirm = false
                    }) {
                        Text("Ya")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteConfirm = false }) {
                        Text("Tidak")
                    }
                }
            )
        }
    }
}

@Composable
fun HistoryCard(item: HistoryItem, onDelete: () -> Unit) {
    when (item) {
        is HistoryItem.Translation -> {
            TranslationHistoryCard(item, onDelete)
        }

        is HistoryItem.DownloadedDictionary -> {
            DownloadedDictionaryHistoryCard(item, onDelete)
        }
    }
}

@Composable
fun TranslationHistoryCard(item: HistoryItem.Translation, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Translate",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color(0xFF008E9B), RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = item.timestamp.formatTimestamp(),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun DownloadedDictionaryHistoryCard(item: HistoryItem.DownloadedDictionary, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dictionary",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color(0xFF00B4D8), RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (item.localImagePath != null) {
                    Image(
                        painter = rememberImagePainter(
                            data = File(item.localImagePath),
                            builder = {
                                crossfade(true)
                                placeholder(R.drawable.ic_launcher_foreground)
                                error(R.drawable.ic_launcher_background)
                            }
                        ),
                        contentDescription = item.title,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = item.timestamp.formatTimestamp(),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Hapus",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

fun Long.formatTimestamp(): String {
    val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(this))
}
