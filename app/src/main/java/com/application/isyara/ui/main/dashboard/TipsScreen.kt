package com.application.isyara.ui.main.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.isyara.R
import com.application.isyara.utils.main.AppHeaderMain

data class TipItem(val iconRes: Int, val title: String, val description: String)

@Composable
fun TipsScreen(onBackClick: () -> Unit, navController: NavController) {
    val tipsList = listOf(
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_1),
            description = stringResource(R.string.description_1)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_2),
            description = stringResource(R.string.description_2)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_3),
            description = stringResource(R.string.description_3)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_4),
            description = stringResource(R.string.description_4)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_5),
            description = stringResource(R.string.description_5)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_6),
            description = stringResource(R.string.description_6)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_7),
            description = stringResource(R.string.description_7)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_8),
            description = stringResource(R.string.description_8)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_9),
            description = stringResource(R.string.description_9)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_10),
            description = stringResource(R.string.description_10)
        ),
        TipItem(
            iconRes = R.drawable.ic_tips,
            title = stringResource(R.string.title_11),
            description = stringResource(R.string.description_11)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // Header
        AppHeaderMain(
            title = stringResource(R.string.tips_isyarat),
            onBackClick = onBackClick,
            backgroundColor = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar Tips
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tipsList) { tip ->
                TipCard(tip)
            }
        }
    }
}

@Composable
fun TipCard(tip: TipItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color.White),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = tip.iconRes),
                contentDescription = tip.title,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF008E9B)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = tip.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
