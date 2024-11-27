package com.application.isyara.ui.main.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.utils.main.AppHeaderMain

@Composable
fun AlphabetScreen(
    title: String,
    descriptionTitle: String,
    description: String,
    alphabetTitle: String,
    alphabetList: List<String>,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(0xFFF7F7F7))
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                InfoCard(
                    title = descriptionTitle,
                    description = description
                )

                Spacer(modifier = Modifier.height(16.dp))

                SearchBar()

                Spacer(modifier = Modifier.height(16.dp))

                AlphabetList(
                    title = alphabetTitle,
                    categories = alphabetList
                )
            }
        }

        AppHeaderMain(
            title = title,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(Color(0xFF008E9B), Color(0xFF00B4D8))
            ),
            onBackClick = { /* Handle Back Navigation */ }
        )
    }
}


@Composable
fun InfoCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {  },
        placeholder = { Text("Cari isyarat...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color(0xFF008E9B),
            cursorColor = Color(0xFF008E9B),
            placeholderColor = Color.Gray,
            backgroundColor = Color.White
        )
    )
}

@Composable
fun AlphabetList(title: String, categories: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            categories.forEach { category ->
                AlphabetItem(category)
            }
        }
    }
}

@Composable
fun AlphabetItem(category: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Isyarat untuk $category",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}
