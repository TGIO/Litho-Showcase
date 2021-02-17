package com.github.tgio.uefa.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.tgio.uefa.databinding.ActivityChooserBinding
import com.github.tgio.uefa.ui.litho.StyleUEFA

class ChooserActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChooserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.game1.setOnClickListener {
            MainActivity.start(this, 1, StyleUEFA.Blue)
        }

        binding.game2.setOnClickListener {
            MainActivity.start(this, 2, StyleUEFA.Blue)
        }

        binding.game3.setOnClickListener {
            MainActivity.start(this, 3, StyleUEFA.Dark)
        }
    }
}