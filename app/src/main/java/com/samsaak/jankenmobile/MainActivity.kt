package com.samsaak.jankenmobile

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.samsaak.jankenmobile.landingPage.LandingPageActivity
import com.samsaak.jankenmobile.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_result.view.*

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
    private var isFinish: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//      Set player choice
        initialConfig()
    }

    private fun onImageClicked(value: String, key: String) {
        if (data?.foe == "CPU") {
            val randomCom = optionsChoice.random()
            playerOneChoice = value
            comChoice = randomCom
            setBackground(value, comChoice)
            Log.i("Player Input", "Player Use: $value")

            checkResult()
        } else {
            if (key == "player") twoPlayerSet(value, "", key)
            if (key == "cpu") twoPlayerSet("", value, key)
        }
    }

    private fun resetButton() {
        playerOneChoice = ""
        comChoice = ""
        isFinish = false
        setBackground("", "")
        middleTextStatus.text = getString(R.string.versus)
        middleTextStatus.setBackgroundColor(Color.TRANSPARENT)
        middleTextStatus.setTextColor(resources.getColor(R.color.chilli_red))
        middleTextStatus.setTextSize(1, 36F)
    }

    private fun initialConfig() {
        binding.run {
            titlePlayer.text = data?.player
            titleCom.text = data?.foe

            //      set refresh game
            refresh.setOnClickListener {
                resetButton()
            }
            icnClose.setOnClickListener { view ->
                startActivity(
                    Intent(this@MainActivity, LandingPageActivity::class.java)
                        .putExtra("name", "yuda")
                )
            }
        }

        batuPlayer = findViewById(R.id.optbatu)
        guntingPlayer = findViewById(R.id.optGunting)
        kertasPlayer = findViewById(R.id.optKertas)
        batuCom = findViewById(R.id.optbatuCom)
        guntingCom = findViewById(R.id.optGuntingCom)
        kertasCom = findViewById(R.id.optKertasCom)
        middleTextStatus = findViewById(R.id.txtStatus)

        playerOneChoice = ""
        comChoice = ""


        batuPlayer.setOnClickListener {
            onImageClicked("Batu", "player")
        }
        guntingPlayer.setOnClickListener {
            onImageClicked("Gunting", "player")
        }
        kertasPlayer.setOnClickListener {
            onImageClicked("Kertas", "player")
        }
        if (data?.foe == "Player 2") {

            batuCom.setOnClickListener {
                onImageClicked("Batu", "cpu")
            }
            guntingCom.setOnClickListener {
                onImageClicked("Gunting", "cpu")
            }
            kertasCom.setOnClickListener {
                onImageClicked("Kertas", "cpu")
            }
        }
    }

    private fun setBackground(playerVal: String, comVal: String) {
        val backgroundColor = resources.getColor(R.color.baby_blue)
        batuPlayer.setBackgroundColor(Color.TRANSPARENT)
        guntingPlayer.setBackgroundColor(Color.TRANSPARENT)
        kertasPlayer.setBackgroundColor(Color.TRANSPARENT)
        batuCom.setBackgroundColor(Color.TRANSPARENT)
        guntingCom.setBackgroundColor(Color.TRANSPARENT)
        kertasCom.setBackgroundColor(Color.TRANSPARENT)

        when (playerVal) {
            "Batu" -> batuPlayer.setBackgroundColor(backgroundColor)
            "Gunting" -> guntingPlayer.setBackgroundColor(backgroundColor)
            "Kertas" -> kertasPlayer.setBackgroundColor(backgroundColor)
        }

        when (comVal) {
            "Batu" -> batuCom.setBackgroundColor(backgroundColor)
            "Gunting" -> guntingCom.setBackgroundColor(backgroundColor)
            "Kertas" -> kertasCom.setBackgroundColor(backgroundColor)
        }
    }

    private fun twoPlayerSet(player1: String, player2: String, key: String) {
        val backgroundColor = resources.getColor(R.color.baby_blue)

        if (isFinish == true) return
        when {
            key == "player" -> {
                playerOneChoice = player1
                when (player1) {
                    "Batu" -> batuPlayer.setBackgroundColor(backgroundColor)
                    "Gunting" -> guntingPlayer.setBackgroundColor(backgroundColor)
                    "Kertas" -> kertasPlayer.setBackgroundColor(backgroundColor)
                }
                Toast.makeText(this, "fill player one $player1", Toast.LENGTH_SHORT).show()
            }
            key == "cpu" -> {
                comChoice = player2
                when (player2) {
                    "Batu" -> batuCom.setBackgroundColor(backgroundColor)
                    "Gunting" -> guntingCom.setBackgroundColor(backgroundColor)
                    "Kertas" -> kertasCom.setBackgroundColor(backgroundColor)
                }
                Toast.makeText(this, "fill player two $player2", Toast.LENGTH_SHORT).show()
            }
        }
        if (playerOneChoice != "" && comChoice != "") {
            checkResult()
        }
    }

    private fun checkResult() {
        val result = computeResult()
        middleTextStatus.text = result
        middleTextStatus.setPadding(36, 12, 36, 12)
        middleTextStatus.setTextColor(Color.WHITE)
        middleTextStatus.setTextSize(0, 48F)

        when (result) {
            "Draw" -> middleTextStatus.setBackgroundColor(resources.getColor(R.color.background_blue))
            else -> middleTextStatus.setBackgroundColor(resources.getColor(R.color.winning_background))
        }

        isFinish = true
        Log.i("Result", "Game Result: $result")
    }

    private fun computeResult(): String {
        if (playerOneChoice == "" && comChoice == "") return "Vs"
        val result = when {
            (playerOneChoice == "Kertas" && comChoice == "Batu") -> {
                "${data?.player} Menang !!"
            }
            (playerOneChoice == "Kertas" && comChoice == "Gunting") -> {
//                if (data?.foe == "Player 2") {
//                    "Pemain 2 Menang"
//                } else "Computer Menang !!"
                "${data?.foe} Menang"
            }
            (playerOneChoice == "Gunting" && comChoice == "Batu") -> {
//                if (data?.foe == "Player 2") {
//                    "Pemain 2 Menang"
//                } else "Computer Menang !!"
                "${data?.foe} Menang"
            }
            (playerOneChoice == "Gunting" && comChoice == "Kertas") -> {
                "${data?.player} Menang !!"
            }
            (playerOneChoice == "Batu" && comChoice == "Gunting") -> {
                "${data?.player} Menang !!"
            }
            (playerOneChoice == "Batu" && comChoice == "Kertas") -> {
//                if (data?.foe == "Player 2") {
//                    "Pemain 2 Menang"
//                } else "Computer Menang !!"
                "${data?.foe} Menang"
            }
            else -> "Seri !!"
        }
        dialogModal(result)
        return result
    }

    private fun dialogModal(result: String) {
        val dialogView =
            LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_result, null, false)
        val dialogBuilder = AlertDialog.Builder(this@MainActivity)
        dialogBuilder.setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.setCancelable(false)

        dialogView.txtDialogResult.text = result
        dialogView.btnMainLagi.setOnClickListener { viewDialog ->
            resetButton()
            dialog.dismiss()
        }
        dialogView.btnKembaliMenu.setOnClickListener { viewDialog ->
            resetButton()
            startActivity(
                Intent(this@MainActivity, LandingPageActivity::class.java)
                    .putExtra("name", "yuda")
            )
        }
        dialog.show()
    }

    override fun onBackPressed() {
        Log.i("TAGBack", "onBackPressed: ")
        finishAffinity()
    }
}