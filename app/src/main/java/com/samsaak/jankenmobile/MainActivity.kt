package com.samsaak.jankenmobile

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    // Init options
    private val optionsChoice = arrayOf("Batu", "Gunting", "Kertas")

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
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//      Set player choice
        initialConfig()

//      set refresh game
        val refreshButton = findViewById<ImageView>(R.id.refresh)
        refreshButton.setOnClickListener {
            playerOneChoice = ""
            comChoice = ""
            setBackground("", "")
            middleTextStatus.text = getString(R.string.versus)
            middleTextStatus.setBackgroundColor(Color.TRANSPARENT)
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
        middleTextStatus.setBackgroundColor(resources.getColor(R.color.baby_blue))
        middleTextStatus.setPadding(36, 12, 36, 12)

        Log.i("Result", "Game Result: $result")
    }

    private fun computeResult(): String {
        if(playerOneChoice == "" && comChoice == "") return "Vs"
        val result = when {
            (playerOneChoice == "Kertas" && comChoice == "Batu") -> {
                "Player 1 Win !!"
            }
            (playerOneChoice == "Kertas" && comChoice == "Gunting") -> {
                "Com Win !!"
            }
            (playerOneChoice == "Gunting" && comChoice == "Batu") -> {
                "Com Win !!"
            }
            (playerOneChoice == "Gunting" && comChoice == "Kertas") -> {
                "Player 1 Win !!"
            }
            (playerOneChoice == "Batu" && comChoice == "Gunting") -> {
                "Player 1 Win !!"
            }
            (playerOneChoice == "Batu" && comChoice == "Kertas") -> {
                "Com Win !!"
            }
            else -> "Draw"
        }
        return result
    }

}