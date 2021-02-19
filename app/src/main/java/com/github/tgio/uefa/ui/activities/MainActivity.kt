package com.github.tgio.uefa.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.github.tgio.uefa.R
import com.github.tgio.uefa.databinding.ActivityMainBinding
import com.github.tgio.uefa.statefull.StatefulData
import com.github.tgio.uefa.ui.fragments.TimelineFragment
import com.github.tgio.uefa.ui.fragments.TimelineFragmentAdapter
import com.github.tgio.uefa.ui.litho.LithoStyle
import com.github.tgio.uefa.ui.litho.StyleUEFA
import com.github.tgio.uefa.ui.vm.TimelineViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm by viewModel<TimelineViewModel>()

    companion object {
        private const val GAME = "game"
        private const val GAME2 = "game23ikL"
        private const val STYLE = "style"

        fun start(context: Context, game: Int, style: LithoStyle) {
            context.startActivity(
                Intent(context, MainActivity::class.java).apply {
                    putExtras(
                        bundleOf(
                            GAME to game,
                            STYLE to style
                        )
                    )
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.setGameId(intent.extras?.getInt(GAME) ?: 0)
        vm.setStyle(intent.extras?.getParcelable(STYLE) ?: StyleUEFA.Blue)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        vm.state.observe(this) {
            when (it) {
                is StatefulData.Success -> {
                    binding.matchHeaderView.setGameData(it.data.matchInfo)
                }
            }
        }

        with(vm.getStyle()) {
            val colorHighlight = ContextCompat.getColor(this@MainActivity, colorHighlight)
            findViewById<ImageView>(R.id.backdrop).setBackgroundResource(backgroundTop)
            binding.viewpager.setBackgroundResource(backgroundBot)
            binding.tabs.setTabTextColors(Color.WHITE, colorHighlight)
            binding.tabs.setSelectedTabIndicatorColor(colorHighlight)
            binding.matchHeaderView.setStyle(this)
            binding.collapsingToolbar.setContentScrimColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    colorBackground
                )
            )
        }

        binding.viewpager.adapter = TimelineFragmentAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = getString(TimelineFragment.TimelineType.values()[position].titleRes)
            binding.viewpager.setCurrentItem(tab.position, true)
        }.attach()
    }
}
