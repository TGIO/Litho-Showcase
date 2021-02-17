package com.github.tgio.uefa.ui.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.tgio.uefa.api.MatchRepository
import com.github.tgio.uefa.api.datasources.MockDataSource
import com.github.tgio.uefa.api.components.IDataSource
import com.github.tgio.uefa.api.models.MatchInfo
import com.github.tgio.uefa.statefull.StatefulData
import com.github.tgio.uefa.statefull.IThreadChecker
import com.github.tgio.uefa.statefull.LooperChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TimelineViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val testDispatecher = Dispatchers.Unconfined

    @Before
    fun setup() {
        LooperChecker.overrideThreadChecker(object : IThreadChecker {
            override fun isMainThread(): Boolean {
                return false
            }
        })
    }

    private val mockDataSource = MockDataSource()

    private val testDatasource = object: IDataSource {
        @Suppress("TooGenericExceptionThrown")
        override suspend fun getMatchInfo(gameId: Int): MatchInfo = withContext(testDispatecher) {
            when(gameId) {
                0 -> throw Exception("Match not found!")
                
                else -> mockDataSource.getMatchInfo(gameId)
            }
        }
    }

    private val repository = MatchRepository(testDatasource, testDispatecher)

    @Test
    fun testSuccess() {
        val vm = TimelineViewModel(repository)
        Assert.assertTrue(vm.state.value == null)
        vm.getMatchInfo(777)
        Assert.assertTrue(vm.state.value is StatefulData.Success)
        Assert.assertEquals("Bayern", (vm.state.value as StatefulData.Success).data.matchInfo.teamA.name)
    }

    @Test
    fun testError() {
        val vm = TimelineViewModel(repository)
        Assert.assertTrue(vm.state.value == null)
        vm.getMatchInfo(0)
        Assert.assertTrue(vm.state.value is StatefulData.Error)
        Assert.assertEquals("Match not found!", (vm.state.value as StatefulData.Error).throwable.message)
    }
}
