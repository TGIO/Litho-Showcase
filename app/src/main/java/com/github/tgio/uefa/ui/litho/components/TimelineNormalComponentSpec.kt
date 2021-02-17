package com.github.tgio.uefa.ui.litho.components

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.TextAlignment
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaPositionType
import com.github.tgio.uefa.R
import com.github.tgio.uefa.api.models.TimelineStat
import com.github.tgio.uefa.ui.litho.LithoStyle
import java.util.*

@LayoutSpec
object TimelineNormalComponentSpec {

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop model: TimelineStat,
        @Prop style: LithoStyle
    ): Component {
        return Column.create(c)
            .heightDip(55F)
            .backgroundColor(c.getColor(style.color_item_background))
            .child(
                Text.create(c)
                    .text(model.title)
                    .textColor(Color.WHITE)
                    .alignSelf(YogaAlign.CENTER)
                    .alignment(TextAlignment.CENTER)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .heightPercent(100F)
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
                    .text(model.teamA.toString())
                    .textColor(Color.WHITE)
                    .alignment(TextAlignment.LEFT)
                    .verticalGravity(VerticalGravity.CENTER)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.LEFT, 16F)
                    .heightPercent(100F)
                    .shouldIncludeFontPadding(false)
                    .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))
                    .textSizeSp(21F)
            )
            .child(
                Text.create(c)
                    .text(model.teamB.toString())
                    .textColor(Color.WHITE)
                    .alignment(TextAlignment.RIGHT)
                    .verticalGravity(VerticalGravity.CENTER)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.RIGHT, 16F)
                    .heightPercent(100F)
                    .shouldIncludeFontPadding(false)
                    .typeface(ResourcesCompat.getFont(c.androidContext, R.font.champions_bold))
                    .textSizeSp(21F)
            )
            .child(
                Row.create(c)
                    .positionType(YogaPositionType.ABSOLUTE)
                    .positionDip(YogaEdge.BOTTOM, 0F)
                    .positionDip(YogaEdge.START, 16F)
                    .positionDip(YogaEdge.END, 16F)
                    .child(
                        Image.create(c)
                            .drawable(
                                buildRoundedRect(c, c.getColor(style.color_team_a), 3)
                            )
                            .heightDip(2.5F)
                            .widthPercent(50F)
                            .marginDip(YogaEdge.END, 2F)
                    )
                    .child(
                        Image.create(c)
                            .drawable(
                                buildRoundedRect(c, c.getColor(style.color_team_b), 3)
                            )
                            .heightDip(2.5F)
                            .widthPercent(50F)
                            .marginDip(YogaEdge.START, 2F)
                    )
            )
            .build()
    }

    private fun buildRoundedRect(c: ComponentContext, color: Int, cornerRadiusDp: Int): Drawable? {
        val cornerRadiusPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, cornerRadiusDp.toFloat(), c.resources.displayMetrics
        )
        val radii = FloatArray(8)
        Arrays.fill(radii, cornerRadiusPx)
        val roundedRectShape = RoundRectShape(radii, null, radii)
        val drawable = ShapeDrawable(roundedRectShape)
        drawable.paint.color = color
        return drawable
    }
}
