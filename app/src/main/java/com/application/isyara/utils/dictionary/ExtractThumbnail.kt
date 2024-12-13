package com.application.isyara.utils.dictionary

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import timber.log.Timber

fun extractThumbnail(localPath: String): ImageBitmap? {
    return try {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(localPath)
        val bitmap: Bitmap = retriever.getFrameAtTime(1000000) ?: return null
        retriever.release()
        val imageBitmap = bitmap.asImageBitmap()
        Timber.d("Thumbnail extracted from localPath: $localPath")
        imageBitmap
    } catch (e: Exception) {
        Timber.e(e, "Failed to extract thumbnail from localPath: $localPath")
        null
    }
}

fun extractThumbnailFromUrl(videoUrl: String): ImageBitmap? {
    return try {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(videoUrl, HashMap())
        val bitmap: Bitmap = retriever.getFrameAtTime(1000000) ?: return null
        retriever.release()
        val imageBitmap = bitmap.asImageBitmap()
        Timber.d("Thumbnail extracted from videoUrl: $videoUrl")
        imageBitmap
    } catch (e: Exception) {
        Timber.e(e, "Failed to extract thumbnail from videoUrl: $videoUrl")
        null
    }
}
