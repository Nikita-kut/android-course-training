package com.example.skillbox.kotlin.loopshome

fun main() {
    print("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return
    println("Вы ввели число: $n")
    println("Сумма с помощью While = ${calculatByWhile(n)}")
    println("Сумма с помощью While и return = ${calculatByWhileInfinite(n)}")
    println("Сумма с помощью While и break = ${calculatByWhileBreak(n)}")
    println("Сумма с помощью Do While = ${calculateSumDoWhile(n)}")
    // printEvenNumbers(n)
    println("Сумма с помощью for = ${calculateSumFor(n)}")
    printEvenNumberFor(n)
}

fun calculatByWhile(n: Int): Long {
    var sum: Long = 0
    var currentNum: Int = 0
    while (currentNum <= n) {
        sum = sum + currentNum
        currentNum++
    }
    return sum
}

fun calculatByWhileInfinite(n: Int): Long {
    var sum: Long = 0
    var currentNum: Int = 0
    while (true) { //Бесконечный цикл
        if (currentNum > n) return sum
        sum = sum + currentNum
        currentNum++
    }
}

fun calculatByWhileBreak(n: Int): Long {
    var sum: Long = 0
    var currentNum: Int = 0
    while (true) { //Бесконечный цикл
        if (currentNum > n) break
        sum = sum + currentNum
        currentNum++
    }
    return sum
}

fun printEvenNumbers(n: Int) {
    var currentNumber = 0
    while (currentNumber <= n) {
        val numberBefore: Int = currentNumber
        currentNumber++
        if (numberBefore % 2 == 1) {
            continue
        } else println(numberBefore)
    }
}

fun calculateSumDoWhile(n: Int): Long {
    var sum: Long = 0
    var currentNum: Int = 5
    do {
        sum = currentNum + sum
        currentNum++
    } while (currentNum <= n)
    return sum
}

fun calculateSumFor(n: Int): Long {
    var sum: Long = 0
    val range = 0..n
    for (currentNumber in range) {
        sum = sum + currentNumber
    }
    return sum
}

fun printEvenNumberFor(n: Int) {
    val range: IntProgression = n downTo 0 step 2
    for (currentNumber in range)
        println(currentNumber)
}