package com.github.tgio.uefa.api.models

class MatchInfo(
    val team_a: MatchTeamInfo,
    val team_b: MatchTeamInfo,
    val timing: String,
    val duration: String,
    val group: String,
    val stats: Map<String, Array<TimelineStat>>
)