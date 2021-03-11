package com.nikita.kut.android.a16_lists_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class WitcherPerson() : Parcelable {

    @kotlinx.parcelize.Parcelize
    data class Witchers(
        val name: String,
        val age: Int,
        val gender: String,
        val school: String,
        val avatarLink: String
    ) : WitcherPerson(), Parcelable

    @kotlinx.parcelize.Parcelize
    data class WildHunt(
        val name: String,
        val color: String,
        val avatarLink: String
    ) : WitcherPerson(), Parcelable

    @kotlinx.parcelize.Parcelize
    data class Monsters(
        val name: String,
        val kind: String,
        val size: String,
        val avatarLink: String
    ) : WitcherPerson(), Parcelable

}
