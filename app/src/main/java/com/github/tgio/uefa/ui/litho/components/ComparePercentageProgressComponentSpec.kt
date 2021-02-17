@file:Suppress("MagicNumber")

package com.github.tgio.uefa.ui.litho.components

import android.content.Context
import android.graphics.Color
import app.futured.donut.DonutProgressView
import app.futured.donut.DonutSection
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.MountSpec
import com.facebook.litho.annotations.OnCreateMountContent
import com.facebook.litho.annotations.OnMount
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.ResType
import com.github.tgio.uefa.misc.toDp

@MountSpec
object ComparePercentageProgressComponentSpec {

    @OnCreateMountContent
    fun onCreateMountContent(c: Context): DonutProgressView {
        return DonutProgressView(c)
    }

    @OnMount
    fun onMount(
        c: ComponentContext,
        donutProgressView: DonutProgressView,
        @Prop(resType = ResType.COLOR) color: Int,
        @Prop isLeft: Boolean
    ) {
        val section1 = DonutSection(
            name = "section_1",
            color = color,
            amount = 1f
        )

        donutProgressView.bgLineColor = Color.TRANSPARENT
        donutProgressView.cap = 2F
        donutProgressView.gapAngleDegrees = if (isLeft) 90F else 270F
        donutProgressView.gapWidthDegrees = 10F
        donutProgressView.strokeWidth = 3.toDp()
        donutProgressView.gapWidthDegrees = 0F
//        donutProgressView.animateChanges = false
        donutProgressView.submitData(listOf(section1))
    }
}
