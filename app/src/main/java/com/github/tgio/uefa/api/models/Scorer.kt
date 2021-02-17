package com.github.tgio.uefa.api.models

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

class Scorer(
    val name: String,
    val time: String
) {
    fun getColoredSpan(): Spannable {
        val toSpan = time
        val spannable = SpannableString("$name $toSpan")
        val index = spannable.indexOf(toSpan)
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#99ffffff")),
            index,
            index + toSpan.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }
}
