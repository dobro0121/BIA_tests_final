package com.example.bia_tests

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: String,
    val typeOfTask: String,
    val dateTime: String,
    val store: String,
    val shift: String,
    val speciality: String
): Parcelable {}
