package com.samsaak.jankenmobile

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val optionsChoice = arrayOf("Batu", "Gunting", "Kertas")

    private var playerOneChoice = ""

    private var comChoice = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Set player choice
        val batuPlayerOne = findViewById<ImageView>(R.id.optbatu)
        val guntingPlayerOne = findViewById<ImageView>(R.id.optGunting)
        val kertasPlayerOne = findViewById<ImageView>(R.id.optKertas)

        batuPlayerOne.setOnClickListener {
            onImageClicked( "Batu")
            batuPlayerOne.setBackgroundColor(Color.WHITE)
            guntingPlayerOne.setBackgroundColor(Color.TRANSPARENT)
            kertasPlayerOne.setBackgroundColor(Color.TRANSPARENT)
        }
        guntingPlayerOne.setOnClickListener {
            onImageClicked("Gunting")
            batuPlayerOne.setBackgroundColor(Color.TRANSPARENT)
            guntingPlayerOne.setBackgroundColor(Color.WHITE)
            kertasPlayerOne.setBackgroundColor(Color.TRANSPARENT)
        }
        kertasPlayerOne.setOnClickListener {
            onImageClicked("Kertas")
            batuPlayerOne.setBackgroundColor(Color.TRANSPARENT)
            guntingPlayerOne.setBackgroundColor(Color.TRANSPARENT)
            kertasPlayerOne.setBackgroundColor(Color.WHITE)
        }

//        set refresh game
        val refreshButton = findViewById<ImageView>(R.id.refresh)
        refreshButton.setOnClickListener {
            playerOneChoice = ""
            comChoice = ""
            batuPlayerOne.setBackgroundColor(Color.TRANSPARENT)
            guntingPlayerOne.setBackgroundColor(Color.TRANSPARENT)
            kertasPlayerOne.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun printThis(input: Any) {
        println("Chekthis == $input")
        Log.i("BaGuTa", "Check input $input")

    }

    private fun onImageClicked(value: String) {
        val randomCom = optionsChoice.random()
        playerOneChoice = value
        comChoice = randomCom
        Log.i("On Image Clicked", "onImageClicked: $randomCom $value")

        checkResult()
    }


    private fun checkResult() {
        val middleTextStatus: TextView = findViewById(R.id.txtStatus)
        val result = computeResult()
        middleTextStatus.text = result


        Log.i("Result","$playerOneChoice vs $comChoice with result is $result")
    }

    private fun computeResult(): String {
        if(playerOneChoice == "" && comChoice == "") return "Vs"
        var result = "Vs"
        result = when {
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