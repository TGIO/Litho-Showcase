package com.github.tgio.uefa.api.components

import androidx.lifecycle.liveData
import com.github.tgio.uefa.statefull.StatefulData
import kotlinx.coroutines.CoroutineDispatcher

open class Repository(
    private val dispatcher: CoroutineDispatcher
) {

    fun <T> getData(call: suspend () -> T) =
        liveData<StatefulData<T>>(dispatcher) {
            emit(StatefulData.Loading())
            try {
                val response = call.invoke()
                try {
                    emit(StatefulData.Success(response))
                } catch (t: Exception) {
                    emit(StatefulData.Error(t))
                }
            } catch (e: Exception) {
                emit(StatefulData.Error(e))
            }
        }
}