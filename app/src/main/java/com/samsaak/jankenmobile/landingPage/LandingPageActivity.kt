package com.samsaak.jankenmobile.landingPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samsaak.jankenmobile.*
import com.samsaak.jankenmobile.databinding.ActivityLandingPageBinding

class LandingPageActivity : AppCompatActivity(), OnDataPass {
    private lateinit var binding: ActivityLandingPageBinding

    private val pagerAdapter by lazy {
        PagerAdapter(supportFragmentManager)
    }
    private var playerNamePass: String = "Player 1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            vp.offscreenPageLimit = 3
            pagerAdapter.also { viewPagerAdapter ->
                viewPagerAdapter.addFragment(FirstLandingFragment(), "Landing 1")
                viewPagerAdapter.addFragment(SecondLandingFragment(), "Landing 2")
                viewPagerAdapter.addFragment(ThirdLandingFragment(), "Landing 3")
                vp.adapter = viewPagerAdapter
                dotsIndicator.setViewPager(vp)
            }
            btnNext.setOnClickListener {
                val playerName = playerNamePass

                val player = Player(name = playerName)
                val intent =
                    Intent(Intent(this@LandingPageActivity, MenuActivity::class.java)).apply {
                        putExtra("data", player)
                    }
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onDataPass(data: String) {
        if(data !== "") playerNamePass = data
    }
}