package com.nikita.kut.android.a12_activitylifecycle

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(var message: String) : Parcelable
