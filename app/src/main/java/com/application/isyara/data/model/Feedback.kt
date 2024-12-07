package com.application.isyara.data.model

// Data class untuk request Feedback
data class FeedbackRequest(
    val email: String,
    val feedback: String
)

// Data class untuk response feedback
data class FeedbackResponse(
    val message: String
)

// Data class untuk response gagal (400 dan 500)
data class FeedbackErrorResponse(
    val error: String
)

// Data class untuk history feedback
data class FeedbackHistoryResponse(
    val status: String,
    val data: List<FeedbackHistory>
)

// Data class untuk setiap item dalam history feedback
data class FeedbackHistory(
    val id: String,
    val history: HistoryDetails
)

// Data class untuk detail history feedback
data class HistoryDetails(
    val createdAt: String,
    val email: String,
    val feedback: String
)
