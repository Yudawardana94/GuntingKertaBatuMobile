package com.samsaak.jankenmobile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.samsaak.jankenmobile.LandingPage.LandingPageActivity
import com.samsaak.jankenmobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //set binding
    private lateinit var binding: ActivityMainBinding

    // Init options
    private val optionsChoice = arrayOf("Batu", "Gunting", "Kertas")

    // Get parcelable data
    private val data by lazy {
        intent.getParcelableExtra<Foe>("data")
    }

    private lateinit var playerOneChoice: String

    private lateinit var comChoice: String

    private lateinit var batuPlayer: ImageView
    private lateinit var guntingPlayer: ImageView
    private lateinit var kertasPlayer: ImageView
    private lateinit var batuCom: ImageView
    private lateinit var guntingCom: ImageView
    private lateinit var kertasCom: ImageView
    private lateinit var middleTextStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//        show snackbar
//        val sbar = Snackbar.make(this,"panggil snack bar ${data?.name}", Snackbar.LENGTH_INDEFINITE)
//        binding.run{
//            binding.root.showSnackbar("panggil snack bar ${data?.name}")
//        }
        binding.root.showSnackbar("panggil snack bar ${data?.foe} ${data?.player}")

//      Set player choice
        initialConfig()

//      set refresh game
        val refreshButton = findViewById<ImageView>(R.id.refresh)
        refreshButton.setOnClickListener {
            resetButton()
        }
        val icnClose = findViewById<ImageView>(R.id.icnClose)
        icnClose.setOnClickListener { view ->
            startActivity(
                Intent(this,LandingPageActivity::class.java)
                    .putExtra("name", "yuda")
            )
        }
    }

    private fun onImageClicked(value: String) {
        val randomCom = optionsChoice.random()
        playerOneChoice = value
        comChoice = randomCom
        setBackground(value, comChoice)
        Log.i("Player Input", "Player Use: $value")

        checkResult()
    }

    private fun resetButton() {
        playerOneChoice = ""
        comChoice = ""
        setBackground("", "")
        middleTextStatus.text = getString(R.string.versus)
        middleTextStatus.setBackgroundColor(Color.TRANSPARENT)
        middleTextStatus.setTextColor(resources.getColor(R.color.chilli_red))
        middleTextStatus.setTextSize(1, 36F)

    }

    private fun initialConfig () {
        batuPlayer= findViewById(R.id.optbatu)
        guntingPlayer = findViewById(R.id.optGunting)
        kertasPlayer = findViewById(R.id.optKertas)
        batuCom = findViewById(R.id.optbatuCom)
        guntingCom = findViewById(R.id.optGuntingCom)
        kertasCom = findViewById(R.id.optKertasCom)
        middleTextStatus = findViewById(R.id.txtStatus)

        playerOneChoice = ""
        comChoice = ""

        batuPlayer.setOnClickListener {
            onImageClicked( "Batu")
        }
        guntingPlayer.setOnClickListener {
            onImageClicked("Gunting")
        }
        kertasPlayer.setOnClickListener {
            onImageClicked("Kertas")
        }
    }


    private fun setBackground (playerVal: String, comVal: String) {

        val backgroundColor = resources.getColor(R.color.baby_blue)
        batuPlayer.setBackgroundColor(Color.TRANSPARENT)
        guntingPlayer.setBackgroundColor(Color.TRANSPARENT)
        kertasPlayer.setBackgroundColor(Color.TRANSPARENT)
        batuCom.setBackgroundColor(Color.TRANSPARENT)
        guntingCom.setBackgroundColor(Color.TRANSPARENT)
        kertasCom.setBackgroundColor(Color.TRANSPARENT)

        when {
            (playerVal == "Batu") -> batuPlayer.setBackgroundColor(backgroundColor)
            (playerVal == "Gunting") -> guntingPlayer.setBackgroundColor(backgroundColor)
            (playerVal == "Kertas") -> kertasPlayer.setBackgroundColor(backgroundColor)
        }

        when {
            (comVal == "Batu") -> batuCom.setBackgroundColor(backgroundColor)
            (comVal == "Gunting") -> guntingCom.setBackgroundColor(backgroundColor)
            (comVal == "Kertas") -> kertasCom.setBackgroundColor(backgroundColor)
        }
    }


    private fun checkResult() {
        val result = computeResult()
        middleTextStatus.text = result
        middleTextStatus.setPadding(36, 12, 36, 12)
        middleTextStatus.setTextColor(Color.WHITE)
        middleTextStatus.setTextSize(0, 48F)

        when(result) {
            "Draw" -> middleTextStatus.setBackgroundColor(resources.getColor(R.color.background_blue))
            else -> middleTextStatus.setBackgroundColor(resources.getColor(R.color.winning_background))
        }

        Log.i("Result", "Game Result: $result")
    }

    private fun computeResult(): String {
        if(playerOneChoice == "" && comChoice == "") return "Vs"
        val result = when {
            (playerOneChoice == "Kertas" && comChoice == "Batu") -> {
                "Player 1\nMenang !!"
            }
            (playerOneChoice == "Kertas" && comChoice == "Gunting") -> {
                "Computer\nMenang !!"
            }
            (playerOneChoice == "Gunting" && comChoice == "Batu") -> {
                "Computer\nMenang !!"
            }
            (playerOneChoice == "Gunting" && comChoice == "Kertas") -> {
                "Player 1\nMenang !!"
            }
            (playerOneChoice == "Batu" && comChoice == "Gunting") -> {
                "Player 1\nMenang !!"
            }
            (playerOneChoice == "Batu" && comChoice == "Kertas") -> {
                "Computer\nMenang !!"
            }
            else -> "Draw"
        }
        return result
    }

}