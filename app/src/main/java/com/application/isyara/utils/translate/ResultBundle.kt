package com.application.isyara.utils.translate

import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult

data class ResultBundle(
    val results: List<HandLandmarkerResult>,
    val inferenceTime: Long,
    val inputImageHeight: Int,
    val inputImageWidth: Int,
    val prediction: String
)