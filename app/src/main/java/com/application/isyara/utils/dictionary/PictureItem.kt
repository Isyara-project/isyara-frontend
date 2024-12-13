package com.application.isyara.utils.dictionary

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.application.isyara.navigation.NavRoute
import com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel

@Composable
fun PictureItem(
    image: ImageItems,
    navController: NavController,
    viewModel: DictionaryPictureViewModel,
    isDownloading: Boolean,
    onDownloadClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                val encodedUrl = Uri.encode(image.url)
                navController.navigate("${NavRoute.PictureDetail.route}/$encodedUrl")
            },
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
            // Gambar Thumbnail
            Image(
                painter = rememberImagePainter(data = image.url),
                contentDescription = image.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Informasi Gambar
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = image.name.capitalizeWords(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Deskripsi untuk ${image.name.capitalizeWords()}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            // Tombol Download/Hapus
            IconButton(
                onClick = {
                    if (!image.isDownloaded) {
                        onDownloadClick(image.url)
                    } else {
                        onDeleteClick(image.url)
                    }
                },
                modifier = Modifier.size(28.dp)
            ) {
                when {
                    isDownloading -> {
                        CircularProgressIndicator(
                            color = Color(0xFF008E9B),
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    image.isDownloaded -> {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Hapus",
                            tint = Color.Green,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    else -> {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}