package com.example.skillbox.kotlin.funcproject
import kotlin.math.sqrt
fun main() {
    println(solveEquation2(a =1, b =2, c =1))
}
fun solveEquation2 (a: Int, b: Int, c: Int): Double? {
    val d: Double = (b * b - (4 * a * c)).toDouble()
    return when { a<0.0 -> null
        else -> {
            val x1: Double = ((-b + sqrt(d.toDouble())) / 2 * a)//расчёт х1
            val x2: Double = ((-b - sqrt(d.toDouble())) / 2 * a)//расчёт х2
            val sum = x1 + x2
            sum
        }
    }
}