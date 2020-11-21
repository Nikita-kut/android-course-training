package com.example.skillbox.kotlin.loopshome

fun main() {
    print("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return
    println("Вы ввели число $n")
    println("Введите ещё $n чисел")
    var i = 0
    var k = 0
    var sum = 0
    while (i < n) {
        println("Ввод: ")
        val j = readLine()?.toIntOrNull() ?: continue
        sum +=j
        i++
        if (j>=0) k++
    }
    println("Положительных чисел введено: $k")
    println("Сумма введенных чисел: $sum")
}


