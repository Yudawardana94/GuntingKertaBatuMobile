package com.samsaak.jankenmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.samsaak.jankenmobile.landingPage.LandingPageActivity
import com.samsaak.jankenmobile.databinding.ActivitySplashBinding
import java.util.Timer
import kotlin.concurrent.schedule


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            loadImage()
            Timer().schedule(3000) {
                startActivity(Intent(this@SplashActivity, LandingPageActivity::class.java))
                finish()
            }
        }
    }

    private fun loadImage() = binding.run {
        Glide.with(this@SplashActivity)
            .load(Constant.IMAGE_URL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imgLogo)
    }
}