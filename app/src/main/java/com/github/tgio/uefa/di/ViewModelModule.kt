package com.github.tgio.uefa.di

import com.github.tgio.uefa.ui.vm.TimelineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TimelineViewModel(
            get()
        )
    }
}
