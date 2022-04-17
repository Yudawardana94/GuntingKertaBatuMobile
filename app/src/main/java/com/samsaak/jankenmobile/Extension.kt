package com.samsaak.jankenmobile

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    val sbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    sbar.setAction("tutup") {
        sbar.dismiss()
    }
    sbar.show()
}