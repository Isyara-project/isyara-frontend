package com.application.isyara.utils.translate

import com.application.isyara.utils.translate.SignLanguageRecognizer.Companion.OTHER_ERROR

interface LandmarkerListener {
    fun onResults(resultBundle: ResultBundle)
    fun onError(error: String, errorCode: Int = OTHER_ERROR)
}