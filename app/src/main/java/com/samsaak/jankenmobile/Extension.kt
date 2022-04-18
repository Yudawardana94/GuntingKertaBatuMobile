package com.samsaak.jankenmobile

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    val sbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    sbar.setAction("tutup") {
        sbar.dismiss()
    }
    sbar.show()
}

fun EditText.getUsername(): String? {
    var returnString: String =""
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            returnString = p0.toString()
        }

        override fun afterTextChanged(p0: Editable?) {
            TODO("Not yet implemented")
        }
    })
    return returnString
}