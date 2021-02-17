package com.github.tgio.uefa.api.models

class MatchTeamInfo(
    val logo: Int,
    val name: String,
    val score: Int,
    val redCards: Int,
    val scorers: Array<Scorer>
)