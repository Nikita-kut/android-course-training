package com.example.skillbox.kotlin.funcproject

import kotlin.math.sqrt

fun main() {
val solutionSum = solveEquation(a=2,b=8,c=3)
    println("Сумма корней = $solutionSum")
}

fun solveEquation (a: Int, b: Int, c: Int): Double {
    val d: Double = (b*b-4*a*c).toDouble()//расчёт дискриминанта//
    val x1 = ((-b+sqrt(d))/2*a)//расчёт х1
    val x2 = ((-b- sqrt(d))/2*a)//расчёт х2
    return x1+x2
}


