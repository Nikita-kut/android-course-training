package com.example.skillbox.kotlin.funcproject

import kotlin.math.sqrt

fun main() {
val solutionSum = solveEquation(a=1,b=2,c=1)
    println("Сумма корней = $solutionSum")
}

fun solveEquation (a: Int, b: Int, c: Int): String {
    var d: Double = (b*b-4*a*c).toDouble()//расчёт дискриминанта
    val x1 = ((-b+sqrt(d))/2*a)//расчёт х1
    val x2 = ((-b- sqrt(d))/2*a)//расчёт х2
    return if (d<0) {
        "Уравнение не имеет корней"
    } else if (d=0) {
        (x2).toString()
    }
    else {
        (x1 + x2).toString()
    }
}


