package com.github.tgio.uefa.ui.fragments

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TimelineFragmentAdapter(
    fragmentManager: FragmentManager,
    host: Lifecycle
) : FragmentStateAdapter(fragmentManager, host) {
    override fun getItemCount(): Int = TimelineFragment.TimelineType.values().size

    override fun createFragment(position: Int) = TimelineFragment.newInstance(
        TimelineFragment.TimelineType.values()[position]
    )
}
