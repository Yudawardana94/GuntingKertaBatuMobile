package com.samsaak.jankenmobile.landingPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.samsaak.jankenmobile.*
import com.samsaak.jankenmobile.databinding.ActivityLandingPageBinding
import kotlinx.android.synthetic.main.fragment_third_landing.*

class LandingPageActivity : AppCompatActivity(), OnDataPass {
    private lateinit var binding: ActivityLandingPageBinding

    private val pagerAdapter by lazy {
        PagerAdapter(supportFragmentManager)
    }
    var playerNamePass: String = ""
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
                val playerName = playerNamePass

                Toast.makeText(this@LandingPageActivity, "welcome $playerName", Toast.LENGTH_SHORT).show()
                val player = Player(name= playerName)
                val intent = Intent(Intent(this@LandingPageActivity, MenuActivity::class.java)).apply {
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
        playerNamePass = data
    }
}