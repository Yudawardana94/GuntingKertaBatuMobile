package com.samsaak.jankenmobile.LandingPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.samsaak.jankenmobile.MainActivity
import com.samsaak.jankenmobile.MenuActivity
import com.samsaak.jankenmobile.PagerAdapter
import com.samsaak.jankenmobile.Player
import com.samsaak.jankenmobile.databinding.ActivityLandingPageBinding

class LandingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding

    private val pagerAdapter by lazy {
        PagerAdapter(supportFragmentManager)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            vp.offscreenPageLimit = 3
            pagerAdapter.also { viewPagerAdapter ->
                viewPagerAdapter.addFragment(FirstLandingFragment(),"Landing 1")
                viewPagerAdapter.addFragment(SecondLandingFragment(),"Landing 2")
                viewPagerAdapter.addFragment(ThirdLandingFragment(),"Landing 3")
                vp.adapter = viewPagerAdapter
                dotsIndicator.setViewPager(vp)
            }
//            btnNext.visibility = View.GONE
            btnNext.setOnClickListener {
                val player = Player(name= "yuda")
                val intent = Intent(Intent(this@LandingPageActivity, MenuActivity::class.java)).apply {
                    putExtra("data", player)
                }
                startActivity(intent)
            }
        }
    }
}