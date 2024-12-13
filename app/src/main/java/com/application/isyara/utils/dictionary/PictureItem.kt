@file:OptIn(FlowPreview::class)

package com.application.isyara.utils.dictionary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel
import com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel
import kotlinx.coroutines.FlowPreview

@Composable
fun PictureItem(
    image: ImageItems,
    navController: NavController,
    viewModel: DictionaryPictureViewModel,
    isDownloading: Boolean,
    onDownloadClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    imageName: String
) {
    val isDownloaded = isDownloading

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
                // Menampilkan gambar menggunakan URL
                Image(
                    painter = rememberImagePainter(image.url),
                    contentDescription = "Image for ${imageName}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Informasi gambar
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                // Menampilkan nama gambar yang diekstrak
                Text(
                    text = imageName.capitalizeWords(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Isyarat untuk ${imageName.capitalizeWords()}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            // Tombol untuk Download atau Delete
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        if (!isDownloaded && !isDownloading) {
                            onDownloadClick(image.url)
                        } else if (isDownloaded) {
                            onDeleteClick(image.url)
                        }
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    when {
                        isDownloading -> {
                            ShimmerPlaceholderButton()
                        }

                        isDownloaded -> {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Downloaded",
                                tint = Color.Green,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        else -> {
                            // Menampilkan icon download jika belum diunduh
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = "Download Image",
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
