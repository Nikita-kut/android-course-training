package com.example.skillbox.kotlin.loopshome

fun main() {
    readLine()?.toIntOrNull()
            ?.let { number ->
                println("Вы ввели число $number")
            }
            ?: println("Вы ввели не число")
    val string: String = "Text"
    val nullableString: String? = "String"

    val length: Int = string.length
    val nullableLength: Int? = nullableString?.length

    println(length)
    println(nullableLength)

    nullableString?.reversed()
            ?.find { it == '4' }
            ?.isDigit()

    if (nullableString != null) {
        println("String length is ${nullableString.length}")
    } else {
        println("String is null")
    }
    printLength("Stinrrn")
}

fun printLength(string: String?) {
    string ?: return
    print("Длинна строки = ${string.length}")
}