package com.github.tgio.uefa.api.components

import com.github.tgio.uefa.api.models.MatchInfo

interface IDataSource {
    suspend fun getMatchInfo(gameId: Int): MatchInfo
}