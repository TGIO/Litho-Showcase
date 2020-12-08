package com.github.tgio.uefa.ui.litho.components

import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaPositionType
import com.github.tgio.uefa.R
import com.github.tgio.uefa.api.models.TimelineStat
import com.github.tgio.uefa.ui.litho.LithoStyle

@LayoutSpec
object TimelineComparisonPercentageComponentSpec {
    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop model: TimelineStat,
        @Prop style: LithoStyle,
    ): Component {
        return Column.create(c)
            .heightDip(144F)
            .backgroundColor(c.getColor(style.color_item_background))
            .child(
                Text.create(c)
                    .text(model.title)
                    .textColor(Color.WHITE)
                    .alignSelf(YogaAlign.CENTER)
                    .textSizeSp(12F)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .typeface(ResourcesCompat.getFont(c.androidContext, R.font.sf_pro_regular))
                    .positionDip(YogaEdge.TOP, 66F)
            )
            .child(
                Row.create(c)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.TOP, 59F)
                    .positionDip(YogaEdge.START, 16F)
                    .child(
                        Text.create(c)
                            .text("${model.teamA}")
                            .textColor(Color.WHITE)
                            .alignSelf(YogaAlign.CENTER)
                            .verticalGravity(VerticalGravity.BOTTOM)
                            .textSizeSp(26F)
                            .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))

                    )
                    .child(
                        Text.create(c)
                            .text("%")
                            .textColor(Color.WHITE)
                            .textSizeSp(21F)
                            .alignSelf(YogaAlign.FLEX_END)
                            .verticalGravity(VerticalGravity.BOTTOM)
                            .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))
                    )
            )
            .child(
                Row.create(c)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.TOP, 59F)
                    .positionDip(YogaEdge.END, 16F)
                    .child(
                        Text.create(c)
                            .text("${model.teamB}")
                            .textColor(Color.WHITE)
                            .alignSelf(YogaAlign.CENTER)
                            .textSizeSp(26F)
                            .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))

                    )
                    .child(
                        Text.create(c)
                            .text("%")
                            .textColor(Color.WHITE)
                            .textSizeSp(21F)
                            .alignSelf(YogaAlign.FLEX_END)
                            .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))
                    )
            )
            .child(
                Row.create(c)
                    .heightDip(144F)
                    .widthDip(149F)
                    .alignSelf(YogaAlign.CENTER)
                    .child(
                        ComparePercentageProgressComponent.create(c)
                            .heightDip(135F)
                            .widthDip(144F)
                            .alignSelf(YogaAlign.CENTER)
                            .colorRes(style.color_team_a)
                            .isLeft(true)
                            .marginDip(YogaEdge.RIGHT, 2.75F)
                            .positionType(YogaPositionType.ABSOLUTE)
                    )
                    .child(
                        ComparePercentageProgressComponent.create(c)
                            .heightDip(135F)
                            .widthDip(144F)
                            .alignSelf(YogaAlign.CENTER)
                            .colorRes(style.color_team_b)
                            .isLeft(false)
                            .marginDip(YogaEdge.START, 5.5F)
                            .positionType(YogaPositionType.ABSOLUTE)
                    )
            )
            .build()

    }
}