@file:OptIn(FlowPreview::class)

package com.application.isyara.ui.main.dictionary

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.isyara.utils.dictionary.ShimmerPlaceholderCard
import com.application.isyara.utils.dictionary.VideoItem
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel
import kotlinx.coroutines.FlowPreview

@Composable
fun SibiVideoScreen(navController: NavController, viewModel: DictionaryVideoViewModel = hiltViewModel()) {
    AlphabetScreen(
        title = "Sibi Dictionary",
        descriptionTitle = "Apa itu Sibi?",
        description = "SIBI (Sistem Isyarat Bahasa Indonesia) adalah bahasa isyarat yang digunakan oleh komunitas tunarungu di Indonesia. SIBI dirancang untuk memberikan representasi tata bahasa Indonesia dalam bentuk isyarat.",
        alphabetTitle = "Daftar Video",
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun AlphabetScreen(
    title: String,
    descriptionTitle: String,
    description: String,
    alphabetTitle: String,
    navController: NavController,
    viewModel: DictionaryVideoViewModel = hiltViewModel()
) {
    val combinedVideos by viewModel.combinedVideos.collectAsState()
    val downloadStatus by viewModel.downloadStatus.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // AppHeaderMain
        AppHeaderMain(
            title = title,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
            ),
            onBackClick = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // InfoCard
        InfoCard(
            title = descriptionTitle,
            description = description
        )

        Spacer(modifier = Modifier.height(8.dp))

        // SearchBar
        SearchBar { query ->
            viewModel.onSearchQueryChange(query)
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (val state = combinedVideos) {
            is Result.Idle -> {
                Text(
                    text = "Silakan cari isyarat di atas.",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            is Result.Loading -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(5) {
                        ShimmerPlaceholderCard()
                    }
                }
            }

            is Result.Success -> {
                Text(
                    text = alphabetTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                )

                if (state.data.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tidak ada video ditemukan.",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.data,
                            key = { video -> video.url }
                        ) { video ->
                            VideoItem(
                                video = video,
                                navController = navController,
                                viewModel = viewModel,
                                isDownloading = downloadStatus[video.url] ?: false,
                                onDownloadClick = { title ->
                                    viewModel.downloadItem(video.url, title) { success ->
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "Video '$title' berhasil diunduh.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Gagal mengunduh video '$title'.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                },
                                onDeleteClick = { urlToDelete ->
                                    viewModel.deleteDownloadedItem(urlToDelete) { success ->
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "Video berhasil dihapus.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Gagal menghapus video.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

            is Result.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun InfoCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onSearch(it)
        },
        placeholder = { Text("Cari isyarat...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color(0xFF008E9B),
            cursorColor = Color(0xFF008E9B),
            textColor = Color.Black,
            placeholderColor = Color.Gray,
            backgroundColor = Color.White
        )
    )
}