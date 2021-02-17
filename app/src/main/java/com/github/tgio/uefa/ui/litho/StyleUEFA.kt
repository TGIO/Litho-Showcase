package com.github.tgio.uefa.ui.litho

import android.os.Parcelable
import com.github.tgio.uefa.R
import kotlinx.parcelize.Parcelize

object StyleUEFA {
    val Dark = LithoStyle(
        background_top = R.drawable.bg_black,
        background_bot = R.drawable.bg_dark,
        color_team_a = R.color.orange,
        color_team_b = R.color.maize,
        color_highlight = R.color.tangerine,
        color_background = R.color.black,
        color_item_background = R.color.dark_overlay
    )
    val Blue = LithoStyle(
        background_top = R.drawable.bg_blue,
        background_bot = R.drawable.bg_gradient_blue,
        color_team_a = R.color.light_greenish_blue,
        color_team_b = R.color.lightish_blue,
        color_highlight = R.color.light_greenish_blue,
        color_background = R.color.dark_blue,
        color_item_background = R.color.dark_blue_overlay
    )
}

@Parcelize
class LithoStyle(
    val background_top: Int,
    val background_bot: Int,
    val color_team_a: Int,
    val color_team_b: Int,
    val color_highlight: Int,
    val color_background: Int,
    val color_item_background: Int
): Parcelable