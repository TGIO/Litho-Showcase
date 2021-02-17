package com.github.tgio.uefa.api

import com.github.tgio.uefa.api.components.IDataSource
import com.github.tgio.uefa.api.components.Repository
import kotlinx.coroutines.CoroutineDispatcher

class MatchRepository(
    private val dataSource: IDataSource,
    dispatcher: CoroutineDispatcher
): Repository(dispatcher) {
    fun getMatchInfo(gameId: Int) = getData { dataSource.getMatchInfo(gameId) }
}