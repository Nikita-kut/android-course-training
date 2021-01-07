package com.example.skillbox.kotlin.a08_02_highorderfunctions

data class User(
    val name: String,
    val lastName: String,
    val age: Int = 0
) {
    var innerState: String = ""

    fun getFullNameLenght() = "$name$lastName".length
}