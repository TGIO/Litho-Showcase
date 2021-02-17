package com.github.tgio.uefa.ui.litho.sections

import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.github.tgio.uefa.api.models.TimelineStat
import com.github.tgio.uefa.ui.fragments.TimelineFragment
import com.github.tgio.uefa.ui.litho.LithoStyle

@GroupSectionSpec
object TimelineRootSectionSpec {
    @OnCreateChildren
    fun onCreateChildren(
        s: SectionContext,
        @Prop data: Map<String, Array<TimelineStat>>,
        @Prop style: LithoStyle,
        @Prop type: TimelineFragment.TimelineType
    ): Children {
        val children = Children.create()

        data.forEach {
            children.child(
                TimelineSection.create(s)
                    .title(it.key)
                    .data(it.value)
                    .style(style)
                    .build()
            )
        }

        return children.build()
    }
}
