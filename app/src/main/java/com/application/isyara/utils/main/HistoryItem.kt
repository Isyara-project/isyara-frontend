package com.application.isyara.utils.main

import androidx.compose.ui.graphics.vector.ImageVector

data class HistoryItem(
    val icon: ImageVector,
    val title: String,
    val time: String,
    val onClick: () -> Unit
)