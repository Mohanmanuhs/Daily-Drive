package com.example.goalstracker.presentation.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import kotlin.math.PI
import kotlin.math.sin


@Composable
fun DownsideCurveCutBackground() {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .rotate(180f)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val amplitude = canvasHeight / 15
        val frequency = 0.0009f
        val path = Path()

        path.moveTo(0f, (canvasHeight / 2))

        for (x in 1 until canvasWidth.toInt()) {
            val y = (sin(x * frequency * 2 * PI) * amplitude) + (canvasHeight / 15)
            path.lineTo(x.toFloat(), y.toFloat())
        }

        path.lineTo(canvasWidth, canvasHeight)
        path.lineTo(0f, canvasHeight)
        path.close()
        translate(left = 0f, top = 0f) {
            scale(scaleX = -1f, scaleY = 1f) {
                drawPath(
                    path = path,
                    brush = Brush.linearGradient(
                        listOf(
                            (Color(0xCCFF4646)),
                            Color(0xCC4F2CDF)
                        )
                    ),
                    style = Fill
                )
            }
        }


    }
}