package com.github.tgio.uefa.api.models

class TimelineStat(
    val title: String,
    val teamA: Number,
    val teamB: Number,
    val type: Int = TYPE_SIMPLE,
) {
    companion object {
        const val TYPE_SIMPLE = 0
        const val TYPE_PERCENTAGE = 1
        const val TYPE_COMPARED_PERCENTAGE = 2
    }
}
