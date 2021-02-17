package com.github.tgio.uefa.core.base

import androidx.lifecycle.ViewModel
import com.github.tgio.uefa.statefull.MutableStatefulLiveData
import com.github.tgio.uefa.ui.models.ScreenModel

open class BaseViewModel<SM : ScreenModel> : ViewModel() {
    val state = MutableStatefulLiveData<SM>()
}
