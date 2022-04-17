package com.samsaak.jankenmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.samsaak.jankenmobile.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private val data by lazy {
        intent.getParcelableExtra<Player>("data")
    }

    private lateinit var binding: ActivityMenuBinding
    private lateinit var foe: Parcelable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            binding.root.showSnackbar("panggil snack bar ${data?.name}")

            txtVsKomputer.text = "${data?.name} vs CPU"
            txtVsPemain.text = "${data?.name} vs Player 2"

            imgVsKomputer.setOnClickListener { viewCom ->
                txtVsKomputer.setText("${data?.name} vs CPU")
                foe = Foe(foe = "CPU", player = "${data?.name}")
                val intent = Intent(Intent(this@MenuActivity, MainActivity::class.java)).apply {
                    putExtra("data", foe)
                }
                startActivity(intent)
            }
            imgVsPemain.setOnClickListener { viewPlayer ->
                txtVsPemain.setText("${data?.name} vs Player 2")
                foe = Foe(foe = "Player 2", player = "${data?.name}")
                val intent = Intent(Intent(this@MenuActivity, MainActivity::class.java)).apply {
                    putExtra("data", foe)
                }
                startActivity(intent)
            }

        }
    }
}