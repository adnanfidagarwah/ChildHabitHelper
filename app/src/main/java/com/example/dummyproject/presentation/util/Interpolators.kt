package com.example.dummyproject.presentation.util

import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator

@Suppress("SpellCheckingInspection")
object Interpolators {
    val linear = LinearInterpolator()

    val linearOutSlowInInterpolator = LinearOutSlowInInterpolator()

    val fastOutLinearInInterpolator = FastOutLinearInInterpolator()
}

class LinearOutSlowInInterpolator : PathInterpolator(0f, 0f, 0.2f, 1f)

class FastOutLinearInInterpolator : PathInterpolator(0.4f, 0f, 1f, 1f)