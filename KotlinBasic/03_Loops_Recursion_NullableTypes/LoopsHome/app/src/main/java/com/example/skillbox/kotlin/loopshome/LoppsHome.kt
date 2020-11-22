package com.example.skillbox.kotlin.loopshome

import kotlin.math.abs

fun main() {
    print("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return
    println("Вы ввели число $n")
    println("Введите ещё $n чисел")
    //var i = 0
    var k = 0
    var sum = 0
    // while (i < n) {
    //       println("Ввод: ")
    //      val j = readLine()?.toIntOrNull() ?: continue
    //      sum += j
    //       i++
    //      if (j >= 0) k++
    //  }
    for (current in 1..n) {
        println("Ввод: ")
        var j = readLine()?.toIntOrNull()
        while (j==null) {
            println("Ввод: ")
           j = readLine()?. toIntOrNull() ?: break
        }
        continue
    }
    println("Положительных чисел введено: $k")
    println("Сумма введенных чисел: $sum")
    println("Наибольший общий делитель: ${recurssion(n, sum)}")
}

tailrec fun recurssion(n: Int, sum: Int = 0): Int {
    if (sum == 0) {
        return Math.abs(n)
    } else {
        return recurssion(sum, n % sum)
    }
}


