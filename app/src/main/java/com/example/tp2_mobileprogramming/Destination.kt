package com.example.tp2_mobileprogramming

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val imageResId: Int
) : Parcelable