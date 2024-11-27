package com.application.isyara.ui.main.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.isyara.utils.main.AppHeaderMain

data class SearchResultItem(val title: String, val description: String)

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    searchResults: List<SearchResultItem> = emptyList(),
    onSearchQueryChange: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = "Search",
            onBackClick = onBackClick,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF008E9B),
                    Color(0xFF00B4D8)
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            SearchBar(
                query = query,
                onQueryChange = {
                    query = it
                    onSearchQueryChange(it)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Results
        if (searchResults.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(searchResults) { result ->
                    SearchResultCard(result)
                }
            }
        } else {
            // Empty State
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (query.isEmpty()) "Mulai ketik untuk mencari..." else "Tidak ada hasil untuk pencarian \"$query\"",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Cari di aplikasi...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color(0xFF008E9B),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SearchResultCard(result: SearchResultItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = result.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = result.description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    val dummyResults = listOf(
        SearchResultItem(
            "Translate Bahasa",
            "Gunakan fitur translate untuk belajar bahasa isyarat."
        ),
        SearchResultItem("Kamus Bahasa", "Temukan arti kata di kamus Isyara."),
        SearchResultItem("Tips Belajar", "Pelajari cara mudah belajar bahasa isyarat."),
    )

    SearchScreen(
        onBackClick = { /* Handle Back */ },
        searchResults = dummyResults
    )
}
