package com.example.custom_view_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyCustomView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var foo: (Float) -> Float = { 0f }

    fun setFunction(value: (Float) -> Float): MyCustomView {
        foo = value
        return this
    }

    private val strokePaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }

    private fun Canvas.drawNiceLine(
        startX: Float,
        startY: Float,
        stopX: Float,
        stopY: Float,
        paint: Paint
    ) =
        this.drawLine(
            startX * SCALE + TO_CENTER,
            startY * SCALE + TO_CENTER,
            stopX * SCALE + TO_CENTER,
            stopY * SCALE + TO_CENTER,
            paint
        )

    private val lineStroke = Paint().apply {
        color = Color.RED
        strokeWidth = 10f
    }

    private fun Canvas.drawGraph() {
        var x = -5f
        var y = foo.invoke(x)

        while (x <= 5) {
            val nx = x + 0.1f
            val ny = foo.invoke(nx)
            println("x: $x; y: $y; nx: $nx; ny: $ny")
            this.drawNiceLine(x, y, nx, ny, lineStroke)
            x = nx
            y = ny

        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Края координатной плоскости
        canvas.drawRect(2f, 2f, 998f, 998f, strokePaint)
        // Координатные прямые
        canvas.drawLine(500f, 0f, 500f, 1000f, strokePaint)
        canvas.drawLine(0f, 500f, 1000f, 500f, strokePaint)

        // График функции
        canvas.drawGraph()
    }

    companion object {
        const val SCALE = 100f
        const val TO_CENTER = 500f
    }
}