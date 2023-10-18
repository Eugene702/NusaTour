package com.eugenefeilianputrarangga.dicoding.nusatour.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tour(
    val photo: String,
    val title: String,
    val description: String,
    val price: String
): Parcelable
