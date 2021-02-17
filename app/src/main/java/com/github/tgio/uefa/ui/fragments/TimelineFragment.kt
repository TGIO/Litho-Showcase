package com.github.tgio.uefa.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.sections.Section
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import com.facebook.litho.widget.Progress
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.github.tgio.uefa.R
import com.github.tgio.uefa.core.base.BaseFragment
import com.github.tgio.uefa.ui.litho.sections.TimelineRootSection
import com.github.tgio.uefa.ui.models.MatchInfoScreenModel
import com.github.tgio.uefa.ui.vm.TimelineViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TimelineFragment : BaseFragment<MatchInfoScreenModel>() {
    private lateinit var lithoView: LithoView
    private lateinit var c: ComponentContext
    private lateinit var s: SectionContext
    private lateinit var type: TimelineType
    override val vm by sharedViewModel<TimelineViewModel>()

    enum class TimelineType(val titleRes: Int) {
        OVERVIEW(R.string.tab_overview),
        LINE_UPS(R.string.tab_lineups),
        STATS(R.string.tab_stats),
        MATCH_INFO(R.string.tab_matchinfo)
    }

    companion object {
        fun newInstance(timelineType: TimelineType): TimelineFragment {
            val fragment = TimelineFragment()
            fragment.arguments = bundleOf("type" to timelineType.ordinal)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = TimelineType.values()[arguments?.getInt("type") ?: 0]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LithoView(requireContext()).also {
            c = ComponentContext(requireContext())
            s = SectionContext(c)
            lithoView = it
        }
    }

    private fun setSection(section: Section) {
        lithoView.setComponent(
            RecyclerCollectionComponent.create(s)
                .section(section)
                .disablePTR(true)
                .build()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getMatchInfo()
    }

    override fun onLoading() {
        lithoView.setComponent(
            Progress.create(c)
                .heightDip(48F)
                .marginDip(YogaEdge.TOP, 130F)
                .alignSelf(YogaAlign.CENTER)
                .colorRes(getStyle().color_highlight)
                .build()
        )
    }

    private fun getStyle() = vm.getStyle()

    override fun onSuccess(data: MatchInfoScreenModel) {
        setSection(
            TimelineRootSection.create(s)
                .data(data.matchInfo.stats)
                .type(type)
                .style(getStyle())
                .build()
        )
    }

    override fun onError(throwable: Throwable) {
        lithoView.setComponent(
            Text.create(c).text(throwable.message).build()
        )
    }
}
