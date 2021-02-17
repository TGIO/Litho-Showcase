package com.github.tgio.uefa.api.models

class MatchInfo(
    val teamA: MatchTeamInfo,
    val teamB: MatchTeamInfo,
    val timing: String,
    val duration: String,
    val group: String,
    val stats: Map<String, Array<TimelineStat>>
)
