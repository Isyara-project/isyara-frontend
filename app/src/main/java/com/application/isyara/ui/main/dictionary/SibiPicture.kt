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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.application.isyara.R
import com.application.isyara.data.model.DictionaryPictureItem
import com.application.isyara.utils.dictionary.ImageItems
import com.application.isyara.utils.dictionary.PictureItem
import com.application.isyara.utils.dictionary.ShimmerPlaceholderCard
import com.application.isyara.utils.dictionary.capitalizeWords
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel

@Composable
fun SibiPictureScreen(
    navController: NavController,
    viewModel: DictionaryPictureViewModel = hiltViewModel()
) {
    AlphabetScreen(
        title = stringResource(R.string.dictionary_picture_isyarat),
        descriptionTitle = stringResource(R.string.what_sibi),

        description = stringResource(R.string.description_sibi),
        alphabetTitle = stringResource(R.string.list_picture),
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
    viewModel: DictionaryPictureViewModel
) {
    val context = LocalContext.current
    val state by viewModel.dictionaryPictures.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val downloadState by viewModel.downloadState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App Header
        AppHeaderMain(
            title = title,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
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
        SearchBarPicture(query = searchQuery) { query ->
            viewModel.updateSearchQuery(query)
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (state) {
            is Result.Idle -> {
                Text(
                    text = stringResource(R.string.start_search),
                    color = MaterialTheme.colorScheme.onPrimary,
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

                if ((state as Result.Success<List<DictionaryPictureItem>>).data.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.nothing_picture),
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
                            items = (state as Result.Success<List<DictionaryPictureItem>>).data,
                            key = { it.url }
                        ) { dictionaryPictureItem ->
                            val imageItem = mapToImageItems(dictionaryPictureItem)

                            PictureItem(
                                image = imageItem,
                                navController = navController,
                                viewModel = viewModel,
                                isDownloading = downloadState[imageItem.url] ?: false,
                                onDownloadClick = { url ->
                                    viewModel.downloadPicture(url)
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.picture_success_download),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onDeleteClick = { urlToDelete ->
                                    viewModel.deleteDownloadedPicture(urlToDelete)
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.picture_success_delete),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    }
                }
            }

            is Result.Error -> {
                Text(
                    text = (state as Result.Error).message,
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

fun mapToImageItems(picture: DictionaryPictureItem): ImageItems {
    val imageName = picture.name.capitalizeWords()
    return ImageItems(
        url = picture.url,
        name = imageName,
        isDownloaded = picture.isDownloaded
    )
}

@Composable
fun SearchBarPicture(query: String, onSearch: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = { newQuery ->
            onSearch(newQuery)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        placeholder = { Text(stringResource(R.string.search_isyarat)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            textColor = Color.Black,
            placeholderColor = Color.Gray,
            backgroundColor = Color.White
        )
    )
}