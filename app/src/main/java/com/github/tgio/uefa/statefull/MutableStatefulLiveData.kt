package com.github.tgio.uefa.statefull

import androidx.lifecycle.MutableLiveData

class MutableStatefulLiveData<T> : MutableLiveData<StatefulData<T>>() {
    fun putLoading(loadingData: Any? = null) {
        if (LooperChecker.isMainThread()) {
            this.value = StatefulData.Loading(loadingData)
        } else {
            this.postValue(StatefulData.Loading(loadingData))
        }
    }

    fun putData(data: T) {
        if (LooperChecker.isMainThread()) {
            this.value = StatefulData.Success(data)
        } else {
            this.postValue(StatefulData.Success(data))
        }
    }

    fun putError(error: Throwable) {
        if (LooperChecker.isMainThread()) {
            this.value = StatefulData.Error(error)
        } else {
            this.postValue(StatefulData.Error(error))
        }
    }
}