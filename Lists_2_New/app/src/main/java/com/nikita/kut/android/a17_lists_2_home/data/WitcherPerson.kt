package com.nikita.kut.android.a17_lists_2_home.data

import android.os.Parcelable

sealed class WitcherPerson() : Parcelable {

    @kotlinx.parcelize.Parcelize
    data class Witchers(
        val id: Long,
        val name: String,
        val age: Int,
        val gender: String,
        val school: String,
        val avatarLink: String
    ) : WitcherPerson(), Parcelable

    @kotlinx.parcelize.Parcelize
    data class WildHunt(
        val id: Long,
        val name: String,
        val color: String,
        val avatarLink: String
    ) : WitcherPerson(), Parcelable

    @kotlinx.parcelize.Parcelize
    data class Monsters(
        val id: Long,
        val name: String,
        val kind: String,
        val size: String,
        val avatarLink: String
    ) : WitcherPerson(), Parcelable

}
