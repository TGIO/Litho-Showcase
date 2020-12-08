package com.github.tgio.uefa.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.github.tgio.uefa.R
import com.github.tgio.uefa.api.models.MatchInfo
import com.github.tgio.uefa.databinding.ViewMatchHeaderBinding
import com.github.tgio.uefa.ui.litho.LithoStyle

class MatchHeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewMatchHeaderBinding = ViewMatchHeaderBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setGameData(matchInfo: MatchInfo) {
        with(matchInfo.team_a) {
            binding.team1Logo.setImageResource(logo)
            binding.team1Title.text = name
            binding.teamAscorer1.text = scorers.getOrNull(0)?.getColoredSpan() ?: ""
            binding.teamAscorer2.text = scorers.getOrNull(1)?.getColoredSpan() ?: ""
            if (redCards == 1){
                binding.teamARedCard1.visibility = View.VISIBLE
            }
            if (redCards == 2){
                binding.teamARedCard1.visibility = View.VISIBLE
                binding.teamARedCard2.visibility = View.VISIBLE
            }
        }
        with(matchInfo.team_b) {
            binding.team2Logo.setImageResource(logo)
            binding.team2Title.text = name
            binding.teamBscorer1.text = scorers.getOrNull(0)?.getColoredSpan() ?: ""
            binding.teamBscorer2.text = scorers.getOrNull(1)?.getColoredSpan() ?: ""
            if (redCards == 1){
                binding.teamBRedCard1.visibility = View.VISIBLE
            }
            if (redCards == 2){
                binding.teamBRedCard1.visibility = View.VISIBLE
                binding.teamBRedCard2.visibility = View.VISIBLE
            }
        }
        binding.timing.text = matchInfo.timing
        binding.duration.text = matchInfo.duration
        binding.groupTitle.text = matchInfo.group
        binding.score.text = "${matchInfo.team_a.score} - ${matchInfo.team_b.score}"
    }

    fun setStyle(style: LithoStyle) {
        binding.btnWatchHighlights.setTextColor(style.color_highlight)
        val colorHighlight = ContextCompat.getColor(context, style.color_highlight)
        val unwrappedDrawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_highlights,
            null
        )
        unwrappedDrawable ?: return
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable)
        DrawableCompat.setTint(wrappedDrawable, colorHighlight)
        binding.btnWatchHighlights.setTextColor(colorHighlight)
        binding.btnWatchHighlights.setCompoundDrawablesWithIntrinsicBounds(
            wrappedDrawable,
            null,
            null,
            null
        )
    }
}