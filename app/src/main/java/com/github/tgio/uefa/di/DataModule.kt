package com.github.tgio.uefa.di

import com.github.tgio.uefa.api.MatchRepository
import com.github.tgio.uefa.api.datasources.MockDataSource
import com.github.tgio.uefa.api.datasources.RealDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single {
        Dispatchers.IO
    }
    single {
        MatchRepository(
            dataSource = MockDataSource(),
            dispatcher = get()
        )
    }
    single {
        RealDataSource(
            //Retrofit service here
        )
    }
}