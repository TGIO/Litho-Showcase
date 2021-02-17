package com.github.tgio.uefa.ui.litho.components

import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.TextAlignment
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaPositionType
import com.github.tgio.uefa.R
import com.github.tgio.uefa.api.models.TimelineStat
import com.github.tgio.uefa.ui.litho.LithoStyle

@LayoutSpec
object TimelinePercentageComponentSpec {
    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop model: TimelineStat,
        @Prop style: LithoStyle,
    ): Component {
        return Column.create(c)
            .heightDip(100F)
            .backgroundColor(c.getColor(style.color_item_background))
            .child(
                PercentageProgressComponent.create(c)
                    .colorRes(style.color_team_a)
                    .widthDip(68F)
                    .heightDip(68F)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.BOTTOM, 0F)
                    .positionDip(YogaEdge.LEFT, 16F)
                    .progress(model.teamA.toFloat())
                    .bgColor(Color.parseColor("#26ffffff"))
            )
            .child(
                PercentageProgressComponent.create(c)
                    .colorRes(style.color_team_b)
                    .widthDip(68F)
                    .heightDip(68F)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.BOTTOM, 0F)
                    .positionDip(YogaEdge.RIGHT, 16F)
                    .progress(model.teamB.toFloat())
                    .bgColor(Color.parseColor("#26ffffff"))
            )
            .child(
                Text.create(c)
                    .text(model.title)
                    .textColor(Color.WHITE)
                    .alignSelf(YogaAlign.CENTER)
                    .alignment(TextAlignment.CENTER)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.BOTTOM, 0F)
                    .heightDip(68F)
                    .verticalGravity(VerticalGravity.CENTER)
                    .typeface(
                        ResourcesCompat.getFont(
                            c.androidContext,
                            R.font.sf_pro_regular
                        )
                    )
                    .textSizeSp(12F)
            )
            .child(
                Text.create(c)
                    .text("${model.teamA}%")
                    .textColor(Color.WHITE)
                    .alignment(TextAlignment.CENTER)
                    .verticalGravity(VerticalGravity.CENTER)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.LEFT, 16F)
                    .positionDip(YogaEdge.BOTTOM, 0F)
                    .verticalGravity(VerticalGravity.CENTER)
                    .widthDip(68F)
                    .heightDip(68F)
                    .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))
                    .textSizeSp(21F)
            )
            .child(
                Text.create(c)
                    .text("${model.teamB}%")
                    .textColor(Color.WHITE)
                    .alignment(TextAlignment.CENTER)
                    .verticalGravity(VerticalGravity.CENTER)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.RIGHT, 16F)
                    .positionDip(YogaEdge.BOTTOM, 0F)
                    .verticalGravity(VerticalGravity.CENTER)
                    .widthDip(68F)
                    .heightDip(68F)
                    .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))
                    .textSizeSp(21F)
            )
            .build()
    }
}