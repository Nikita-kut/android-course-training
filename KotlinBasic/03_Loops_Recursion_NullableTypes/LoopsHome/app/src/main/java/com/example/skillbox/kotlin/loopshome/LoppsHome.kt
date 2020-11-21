package com.example.skillbox.kotlin.loopshome

fun main() {
    print("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return
    println("Вы ввели число $n")
    println("Введите ещё $n чисел")
    var i = 0
    //while (i<=n) {
    //    println("Ввод: ")
     //   readLine()?.toIntOrNull() ?: continue
     //   i++
    //}
    for (i in 1..n) {
        println("Ввод: ")
        readLine()?.toIntOrNull() ?: continue
    }
}


