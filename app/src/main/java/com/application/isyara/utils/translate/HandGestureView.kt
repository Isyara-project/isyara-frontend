package com.application.isyara.utils.translate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class HandGestureView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var landmarks: List<PointF> = emptyList()
    private var predictedChar: String = ""

    private val landmarkPaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 8f
        style = Paint.Style.FILL
    }

    private val linePaint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val textPaint = Paint().apply {
        color = Color.YELLOW
        textSize = 60f
        style = Paint.Style.FILL
    }

    fun setLandmarks(landmarks: List<PointF>) {
        this.landmarks = landmarks
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas ?: return

        for (point in landmarks) {
            canvas.drawCircle(point.x * width, point.y * height, 10f, landmarkPaint)
        }

        if (landmarks.size >= 2) {
            canvas.drawLine(
                landmarks[0].x * width, landmarks[0].y * height,
                landmarks[1].x * width, landmarks[1].y * height,
                linePaint
            )
        }

        if (predictedChar.isNotEmpty()) {
            canvas.drawText(
                predictedChar,
                (width / 2f) - 50f,
                (height / 10f),
                textPaint
            )
        }
    }
}
