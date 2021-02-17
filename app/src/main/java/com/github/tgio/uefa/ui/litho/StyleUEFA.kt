@file:Suppress("LongParameterList")

package com.github.tgio.uefa.ui.litho

import android.os.Parcelable
import com.github.tgio.uefa.R
import kotlinx.parcelize.Parcelize

object StyleUEFA {
    val Dark = LithoStyle(
        backgroundTop = R.drawable.bg_black,
        backgroundBot = R.drawable.bg_dark,
        colorTeamA = R.color.orange,
        colorTeamB = R.color.maize,
        colorHighlight = R.color.tangerine,
        colorBackground = R.color.black,
        colorItemBackground = R.color.dark_overlay
    )
    val Blue = LithoStyle(
        backgroundTop = R.drawable.bg_blue,
        backgroundBot = R.drawable.bg_gradient_blue,
        colorTeamA = R.color.light_greenish_blue,
        colorTeamB = R.color.lightish_blue,
        colorHighlight = R.color.light_greenish_blue,
        colorBackground = R.color.dark_blue,
        colorItemBackground = R.color.dark_blue_overlay
    )
}

@Parcelize
class LithoStyle(
    val backgroundTop: Int,
    val backgroundBot: Int,
    val colorTeamA: Int,
    val colorTeamB: Int,
    val colorHighlight: Int,
    val colorBackground: Int,
    val colorItemBackground: Int
) : Parcelable
