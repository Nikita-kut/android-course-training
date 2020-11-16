package com.example.skillbox.kotlin.loopshome

fun main() {
    val maxInt2 = maxInt(15,10)
    print (maxInt2)

}

fun maxInt (a: Int, b: Int): Int {
    val maxValue: Int
    if (a<b) {
        maxValue = b
    } else {
        maxValue = a
    }
    return maxValue

}