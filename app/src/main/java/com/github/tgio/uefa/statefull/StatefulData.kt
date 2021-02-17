package com.github.tgio.uefa.statefull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

open class StatefulData<T> {
    class Success<T>(val data: T) : StatefulData<T>() {
        override fun toString(): String {
            return "Success(data=$data)"
        }
    }

    class Error<T>(val throwable: Throwable) : StatefulData<T>() {
        override fun toString(): String {
            return "Error(throwable=$throwable)"
        }
    }

    class Loading<T>(val loadingData: Any? = null) : StatefulData<T>() {
        override fun toString(): String {
            return "Loading(loadingData=$loadingData)"
        }
    }
}

typealias StatefulLiveData<T> = LiveData<StatefulData<T>>

typealias MediatorStatefulLiveData<T> = MediatorLiveData<StatefulData<T>>
