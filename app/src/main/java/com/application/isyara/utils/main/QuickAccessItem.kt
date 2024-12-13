package com.application.isyara.utils.main

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class QuickAccessItem(
    val imageVector: ImageVector? = null,
    val painter: Painter? = null,
    val label: String,
    val backgroundColor: Color,
    val contentDescription: String
)
