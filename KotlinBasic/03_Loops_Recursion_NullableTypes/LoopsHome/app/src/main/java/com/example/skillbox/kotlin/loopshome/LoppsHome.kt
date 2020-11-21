package com.example.skillbox.kotlin.loopshome

fun main() {
    print("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return
    println("Вы ввели число $n")
    println("Введите ещё $n чисел")
    val number = 6
    while (number<=n) {
        println("Ввод: ")
        readLine()?.toIntOrNull() ?: return
    }
}


