package com.samsaak.jankenmobile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (val name: String) : Parcelable

@Parcelize
data class Foe (val foe: String, val player: String) : Parcelable