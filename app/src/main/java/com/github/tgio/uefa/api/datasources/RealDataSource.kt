package com.github.tgio.uefa.api.datasources

import com.github.tgio.uefa.api.components.IDataSource
import com.github.tgio.uefa.api.models.MatchInfo

class RealDataSource : IDataSource {
    @Suppress("TooGenericExceptionThrown")
    override suspend fun getMatchInfo(gameId: Int): MatchInfo {
        // Retrofit API service get's called here. Data is mapped to 'MatchInfo'
        // then it's passed up the chain to MatchRepository and then to VM and UI essentially.
        throw Exception("Not Implemented Yet!")
    }
}
