package com.example.skillbox.kotlin.loopshome

fun main() {
    print ("Введите число: ")
    val n = readLine()?.toIntOrNull()?: return
    println("Вы ввели число: $n")
}
fun calculate (n:Int): Long {
    var sum: Long = 0
    var currentNum: Int = 0
    while (currentNum <=n) {
        sum = sum + currentNum
    }
}