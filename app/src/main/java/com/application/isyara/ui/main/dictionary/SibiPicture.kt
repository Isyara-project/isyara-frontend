@file:Suppress("PackageDirectoryMismatch")
@file:OptIn(FlowPreview::class)

package com.application.isyara.ui.main.dictionary

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.application.isyara.utils.state.Result
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.isyara.data.model.DictionaryPicture
import com.application.isyara.ui.main.dashboard.SearchBar
import com.application.isyara.utils.dictionary.ImageItems
import com.application.isyara.utils.dictionary.PictureItem
import com.application.isyara.utils.dictionary.ShimmerPlaceholderCard
import com.application.isyara.utils.dictionary.capitalizeWords
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel
import kotlinx.coroutines.FlowPreview

@Composable
fun SibiPictureScreen(
    navController: NavController,
    viewModel: DictionaryPictureViewModel = hiltViewModel()
) {

    // Fetch dictionary pictures when screen is loaded
    LaunchedEffect(Unit) {
        viewModel.fetchDictionaryPictures()
    }

    // Call the AlphabetScreen with the necessary parameters
    AlphabetScreen(
        title = "Sibi Dictionary",
        descriptionTitle = "Apa itu Sibi?",
        description = "SIBI (Sistem Isyarat Bahasa Indonesia) adalah bahasa isyarat yang digunakan oleh komunitas tunarungu di Indonesia. SIBI dirancang untuk memberikan representasi tata bahasa Indonesia dalam bentuk isyarat.",
        alphabetTitle = "Daftar Gambar",
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
    viewModel: DictionaryPictureViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value
    val searchQuery = viewModel.searchQuery.collectAsState().value
    val downloadState = viewModel.downloadState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // App Header
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

        // SearchBar for filtering images
        SearchBar(query = searchQuery) { query ->
            viewModel.updateSearchQuery(query)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Handle different states (Loading, Success, Error)
        when (state) {
            is Result.Idle -> {
                Text(
                    text = "Silakan cari gambar di atas.",
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
                        ShimmerPlaceholderCard()  // Display loading shimmer effect
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
                            text = "Tidak ada gambar ditemukan.",
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
                        items(state.data) { image ->
                            val imageItem = mapToImageItems(image)  // Map to ImageItems

                            PictureItem(
                                image = imageItem,
                                navController = navController,
                                viewModel = viewModel,
                                isDownloading = downloadState[imageItem.url] ?: false,
                                onDownloadClick = { title ->
                                    viewModel.downloadPicture(imageItem.url)
                                    Toast.makeText(
                                        context,
                                        "Gambar '${imageItem.name}' berhasil diunduh.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onDeleteClick = { urlToDelete ->
                                    viewModel.deleteDownloadedPicture(urlToDelete)
                                    Toast.makeText(
                                        context,
                                        "Gambar berhasil dihapus.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                imageName = imageItem.name
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

            else -> {}
        }
    }
}

fun mapToImageItems(dictionaryPicture: DictionaryPicture): ImageItems {
    Log.d("DictionaryPicture", "Word: ${dictionaryPicture.word}")
    return ImageItems(
        url = dictionaryPicture.imageUrl,
        name = dictionaryPicture.word,
        definition = dictionaryPicture.definition
    )
}