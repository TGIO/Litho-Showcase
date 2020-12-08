package com.github.tgio.uefa.ui.litho.components

import android.content.Context
import app.futured.donut.DonutProgressView
import app.futured.donut.DonutSection
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.github.tgio.uefa.misc.toDp

@MountSpec
object PercentageProgressComponentSpec {

    @OnCreateMountContent
    fun onCreateMountContent(c: Context): DonutProgressView {
        return DonutProgressView(c)
    }

    @OnMount
    fun onMount(
        c: ComponentContext,
        donutProgressView: DonutProgressView,
        @Prop(resType = ResType.COLOR) color: Int,
        @Prop(resType = ResType.COLOR) bgColor: Int,
        @Prop progress: Float
    ) {
        val section1 = DonutSection(
            name = "section_1",
            color = color,
            amount = progress
        )

        donutProgressView.bgLineColor = bgColor
        donutProgressView.gapAngleDegrees = 270F
        donutProgressView.cap = 100F
        donutProgressView.strokeWidth = 3.toDp()
        donutProgressView.gapWidthDegrees = 0F
//        donutProgressView.animateChanges = false
        donutProgressView.submitData(listOf(section1))
    }
}