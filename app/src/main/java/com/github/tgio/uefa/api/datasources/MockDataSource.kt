package com.github.tgio.uefa.api.datasources

import com.github.tgio.uefa.R
import com.github.tgio.uefa.api.components.IDataSource
import com.github.tgio.uefa.api.models.MatchInfo
import com.github.tgio.uefa.api.models.MatchTeamInfo
import com.github.tgio.uefa.api.models.Scorer
import com.github.tgio.uefa.api.models.TimelineStat

@Suppress("MagicNumber")
class MockDataSource : IDataSource {
    override suspend fun getMatchInfo(gameId: Int): MatchInfo {
//        delay(Random.nextLong(1000))
        return when (gameId) {
            1 -> game1()
            2 -> game2()
            else -> game3()
        }
    }

    private fun teamA() = MatchTeamInfo(
        logo = R.drawable.juve,
        name = "Juventus",
        score = 0,
        redCards = 0,
        scorers = emptyArray()
    )

    private fun teamB() = MatchTeamInfo(
        logo = R.drawable.barsa,
        name = "Barcelona",
        score = 2,
        redCards = 0,
        scorers = arrayOf(
            Scorer("Dembélé", "14’"),
            Scorer("Messi", "36’+1 (p)"),
        )
    )

    private fun game1(): MatchInfo {
        return MatchInfo(
            teamA = teamA(),
            teamB = teamB(),
            group = "Group G",
            timing = "",
            duration = "Full time",
            stats = data()
        )
    }

    private fun game2(): MatchInfo {
        return MatchInfo(
            teamA = MatchTeamInfo(
                logo = R.drawable.juve,
                name = "Juventus",
                score = 0,
                redCards = 0,
                scorers = emptyArray()
            ),
            teamB = MatchTeamInfo(
                logo = R.drawable.barsa,
                name = "Barcelona",
                score = 2,
                redCards = 0,
                scorers = arrayOf(
                    Scorer("Dembélé", "14’"),
                    Scorer("Messi", "36’+1 (p)"),
                )
            ),
            group = "",
            timing = "",
            duration = "Full time",
            stats = data()
        )
    }

    private fun game3(): MatchInfo {
        return MatchInfo(
            teamA = MatchTeamInfo(
                logo = R.drawable.bayern,
                name = "Bayern",
                score = 2,
                redCards = 1,
                scorers = arrayOf(
                    Scorer("Scorer", "45’+1, 57’"),
                    Scorer("Scorer", "67’ (o.g.)"),
                )
            ),
            teamB = MatchTeamInfo(
                logo = R.drawable.chelsea,
                name = "Chelsea",
                score = 2,
                redCards = 1,
                scorers = arrayOf(
                    Scorer("Scorer name", "36’ (p)"),
                )
            ),
            group = "Agg: 3-3 Bayern won on penalties",
            timing = "(11 - 10p)",
            duration = "Full time",
            stats = data()
        )
    }

    @Suppress("LongMethod")
    private fun data(): Map<String, Array<TimelineStat>> {
        return mapOf(
            "Key stats" to arrayOf(
                TimelineStat("Possession", 40, 60, TimelineStat.TYPE_COMPARED_PERCENTAGE),
                TimelineStat("Attempts", 14, 8),
                TimelineStat("Corners", 4, 1),
                TimelineStat("Offsides", 0, 3),
                TimelineStat("Passing accuracy", 87, 83, TimelineStat.TYPE_PERCENTAGE),
                TimelineStat("Passes completed", 619, 439),
                TimelineStat("Recovered balls", 29, 35),
                TimelineStat("Attempts blocked", 4, 3),
                TimelineStat("Saves", 0, 3),
                TimelineStat("Yellow cards", 1, 4),
                TimelineStat("Red cards", 0, 1),
            ),
            "Attacking" to arrayOf(
                TimelineStat("Penalties scored", 1, 0),
                TimelineStat("Big chances", 2, 0),
                TimelineStat("Attempts", 13, 8),
                TimelineStat("On target", 5, 0),
                TimelineStat("Off target", 5, 5),
                TimelineStat("On target outside penalty area", 2, 0),
                TimelineStat("Off target outside penalty area", 0, 4),
                TimelineStat("Attempts that hit the woodwork", 1, 0),
                TimelineStat("Attempts on post", 1, 0),
                TimelineStat("Free kicks taken", 21, 17),
                TimelineStat("Attacks", 52, 43),
                TimelineStat("Attack organised", 34, 27),
                TimelineStat("Blocked", 3, 4),
                TimelineStat("Dribbles", 10, 5),
                TimelineStat("Run solo into attacking third", 21, 10),
                TimelineStat("Run solo into key area of play", 15, 7),
                TimelineStat("Run solo into penalty area", 6, 12),
            ),
            "Distribution" to arrayOf(
                TimelineStat("Possession", 40, 60, TimelineStat.TYPE_COMPARED_PERCENTAGE),
                TimelineStat("Passing accuracy", 80, 83, TimelineStat.TYPE_PERCENTAGE),
                TimelineStat("Short passes completed", 228, 133),
                TimelineStat("Medium-length passes completed", 401, 309),
                TimelineStat("Long passes completed", 31, 35),
                TimelineStat("Passes attempted", 710, 524),
                TimelineStat("Short passes attempted", 228, 133),
                TimelineStat("Medium-length passes attempted", 423, 339),
                TimelineStat("Long passes attempted", 59, 51),
                TimelineStat("Backward passes attempted", 128, 84),
                TimelineStat("Passes attempted to the left", 201, 145),
                TimelineStat("Passes attempted to the right", 192, 140),
                TimelineStat("Distance covered (Km)", 112.5F, 114.1F),
                TimelineStat("Top speed (Km/h)", 8.7, 8.5),
                TimelineStat("Cross completed", 0, 2),
                TimelineStat("Cross attempted", 7, 10),
                TimelineStat("Deliveries into the attacking third", 48, 44),
                TimelineStat("Deliveries into key areas of play", 44, 25),
                TimelineStat("Deliveries into into penalty area", 10, 5),
            ),
            "Defending" to arrayOf(
                TimelineStat("Penalties conceded", 8, 8),
                TimelineStat("Attempts conceded", 5, 5),
                TimelineStat("Off-target attempts conceded", 5, 5),
                TimelineStat("On-target attempts conceded", 0, 5),
                TimelineStat("Attempts against the woodwork conceded", 1, 0),
                TimelineStat("Tackles", 8, 17),
                TimelineStat("Won", 3, 6),
                TimelineStat("Lost", 5, 11),
                TimelineStat("Clearance attempted", 14, 12),
                TimelineStat("Clearance completed", 7, 5),
            ),
            "Goalkeeping" to arrayOf(
                TimelineStat("Saves", 0, 2),
                TimelineStat("From indirect free kick", 0, 2),
                TimelineStat("From direct free kick", 0, 2),
                TimelineStat("Goals conceded", 0, 2),
                TimelineStat("Claims", 4, 2),
                TimelineStat("Claims high", 3, 1),
                TimelineStat("Low", 1, 1),
                TimelineStat("Clean sheet", 1, 0),
            )
        )
    }
}
