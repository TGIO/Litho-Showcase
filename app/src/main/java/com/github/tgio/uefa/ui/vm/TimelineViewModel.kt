package com.github.tgio.uefa.ui.vm

import androidx.lifecycle.MutableLiveData
import com.github.tgio.uefa.api.MatchRepository
import com.github.tgio.uefa.core.base.BaseViewModel
import com.github.tgio.uefa.statefull.StatefulData
import com.github.tgio.uefa.ui.litho.LithoStyle
import com.github.tgio.uefa.ui.litho.StyleUEFA
import com.github.tgio.uefa.ui.models.MatchInfoScreenModel

class TimelineViewModel(
    private val repository: MatchRepository
) : BaseViewModel<MatchInfoScreenModel>() {

    private val gameId = MutableLiveData<Int>()
    private val style = MutableLiveData(StyleUEFA.Blue)

    fun setGameId(gameId: Int) {
        this.gameId.value = gameId
    }

    fun setStyle(style: LithoStyle) {
        this.style.value = style
    }

    fun getMatchInfo(gameId: Int = getGameId()) {
        if (state.value == null) {
            state.putLoading()
            repository.getMatchInfo(gameId).observeForever {
                when(it) {
                    is StatefulData.Success -> state.putData(MatchInfoScreenModel(it.data))
                    is StatefulData.Error -> state.putError(it.throwable)
                }
            }
        }
    }

    private fun getGameId(): Int {
        return gameId.value ?: 0
    }

    fun getStyle() = style.value ?: StyleUEFA.Blue
}
