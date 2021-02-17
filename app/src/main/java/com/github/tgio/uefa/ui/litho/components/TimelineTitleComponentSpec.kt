package com.github.tgio.uefa.ui.litho.components

import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.EventHandler
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaPositionType
import com.github.tgio.uefa.R
import com.github.tgio.uefa.ui.litho.LithoStyle

@LayoutSpec
object TimelineTitleComponentSpec {
    @Suppress("MagicNumber")
    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop title: String,
        @Prop isExpanded: Boolean,
        @Prop titleClickHandler: EventHandler<ClickEvent>,
        @Prop style: LithoStyle
    ): Component {
        return Column.create(c)
            .child(
                Row.create(c)
                    .heightDip(56F)
                    .backgroundColor(c.getColor(style.colorItemBackground))
                    .child(
                        Text.create(c)
                            .text(title)
                            .textColor(Color.WHITE)
                            .textSizeSp(20F)
                            .marginDip(YogaEdge.START, 16F)
                            .alignSelf(YogaAlign.CENTER)
                            .typeface(
                                ResourcesCompat.getFont(
                                    c.androidContext,
                                    R.font.sf_pro_semibold
                                )
                            )
                    ).child(
                        Image.create(c)
                            .drawableRes(if (isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
                            .positionType(YogaPositionType.ABSOLUTE)
                            .positionDip(YogaEdge.RIGHT, 16F)
                            .alignSelf(YogaAlign.CENTER)
                    )
                    .clickHandler(titleClickHandler)
                    .build()
            )
            .child(
                Row.create(c).heightDip(if (isExpanded) 0F else 8F)
            )
            .build()
    }
}
