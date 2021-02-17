package com.github.tgio.uefa.ui.litho.sections

import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Row
import com.facebook.litho.StateValue
import com.facebook.litho.annotations.*
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.SingleComponentSection
import com.github.tgio.uefa.api.models.TimelineStat
import com.github.tgio.uefa.ui.litho.LithoStyle
import com.github.tgio.uefa.ui.litho.components.TimelineTitleComponent

@GroupSectionSpec
object TimelineSectionSpec {
    @OnCreateChildren
    fun onCreateChildren(
        s: SectionContext,
        @Prop title: String,
        @Prop data: Array<TimelineStat>,
        @Prop style: LithoStyle,
        @State isExpanded: Boolean
    ): Children {
        val children = Children.create()

        children.child(
            SingleComponentSection.create(s)
                .component(
                    TimelineTitleComponent.create(s)
                        .title(title)
                        .isExpanded(isExpanded)
                        .style(style)
                        .titleClickHandler(TimelineSection.onClick(s))
                        .build()
                )
                .build()
        )

        if (isExpanded) {
            children.child(
                TimelineStatsSection.create(s)
                    .data(data)
                    .style(style)
                    .build()
            )
            children.child(
                SingleComponentSection.create(s)
                    .component(
                        Column.create(s)
                            .child(
                                Row.create(s)
                                    .heightDip(16F)
                                    .backgroundColor(s.getColor(style.color_item_background))
                            )
                            .child(
                                Row.create(s)
                                    .heightDip(8F)
                            )
                    )
            )
        }

        return children.build()
    }

    @OnEvent(ClickEvent::class)
    fun onClick(
        s: SectionContext,
    ) {
        TimelineSection.updateIsExpanded(s)
    }

    @OnCreateInitialState
    fun onCreateInitialState(
        s: SectionContext,
        isExpanded: StateValue<Boolean>,
    ) {
        isExpanded.set(false)
    }

    @OnUpdateState
    fun updateIsExpanded(
        isExpanded: StateValue<Boolean>
    ) {
        isExpanded.set(isExpanded.get()?.not())
    }
}
