package com.github.tgio.uefa.ui.litho.sections

import com.facebook.litho.annotations.FromEvent
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.EmptyComponent
import com.facebook.litho.widget.RenderInfo
import com.github.tgio.uefa.api.models.TimelineStat
import com.github.tgio.uefa.ui.litho.LithoStyle
import com.github.tgio.uefa.ui.litho.components.TimelineComparisonPercentageComponent
import com.github.tgio.uefa.ui.litho.components.TimelineNormalComponent
import com.github.tgio.uefa.ui.litho.components.TimelinePercentageComponent

@GroupSectionSpec
object TimelineStatsSectionSpec {
    @OnCreateChildren
    fun onCreateChildren(
        s: SectionContext,
        @Prop data: Array<TimelineStat>,
    ): Children {
        val children = Children.create()

        children.child(
            DataDiffSection.create<TimelineStat>(s)
                .data(data.toList())
                .renderEventHandler(TimelineStatsSection.onRender(s))
                .build()
        )

        return children.build()
    }

    @OnEvent(RenderEvent::class)
    fun onRender(
        s: SectionContext,
        @FromEvent model: TimelineStat,
        @Prop style: LithoStyle
    ): RenderInfo {
        return ComponentRenderInfo.create()
            .component(
                when (model.type) {
                    TimelineStat.TYPE_PERCENTAGE -> TimelinePercentageComponent.create(s)
                        .model(model)
                        .style(style)
                        .build()
                    TimelineStat.TYPE_COMPARED_PERCENTAGE ->
                        TimelineComparisonPercentageComponent.create(s)
                            .model(model)
                            .style(style)
                            .build()
                    TimelineStat.TYPE_SIMPLE -> TimelineNormalComponent.create(s)
                        .model(model)
                        .style(style)
                        .build()
                    else -> EmptyComponent.create(s).build()
                }
            )
            .build()
    }
}
