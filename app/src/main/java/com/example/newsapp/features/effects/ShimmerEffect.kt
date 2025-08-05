package com.example.newsapp.features.effects

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ShimmerEffect(
    modifier: Modifier,
    shimmerWidth: Float = 200f,
    durationMillis: Int = 1000
) {
    val transition = rememberInfiniteTransition(label = "")
    val shimmerTranslateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.White.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.6f)
        ),
        start = Offset(shimmerTranslateAnim.value - shimmerWidth, 0f),
        end = Offset(shimmerTranslateAnim.value, 0f)
    )

    Spacer(
        modifier = modifier
            .background(brush)
    )
}


@Preview(showBackground = true)
@Composable
fun ShimmerEffectPreview(){
    ShimmerEffect(Modifier)
}