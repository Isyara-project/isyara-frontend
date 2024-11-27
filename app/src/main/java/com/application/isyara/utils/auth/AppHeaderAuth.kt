package com.application.isyara.utils.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppHeaderAuth(
    title: String,
    backgroundDrawable: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = backgroundDrawable),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .align(Alignment.Center)
        )
    }
}
