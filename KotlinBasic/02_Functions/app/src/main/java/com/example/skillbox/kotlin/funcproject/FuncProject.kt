package com.example.skillbox.kotlin.funcproject

import kotlin.math.sqrt

fun main() {
    val result = solveEquation(a = 500, b = 15, c = 1)
    println(if (result == null) "Уравнение не имеет корней" else "Сумма корней: $result")
}

fun solveEquation(a: Int, b: Int, c: Int): Double? {
    val d: Double = (b * b - (4 * a * c)).toDouble()
    return when {
        d < 0.0 -> null
        d == 0.0 -> ((-b - sqrt(d.toDouble())) / 2 * a)
        else -> {
            val x1: Double = ((-b + sqrt(d.toDouble())) / 2 * a)//расчёт х1
            val x2: Double = ((-b - sqrt(d.toDouble())) / 2 * a)//расчёт х2
            val sum = x1 + x2
            sum
        }
    }
}