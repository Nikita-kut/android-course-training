package com.example.skillbox.kotlin.collecthome

import android.annotation.SuppressLint
import java.security.Key
import java.util.*
import java.util.regex.Pattern

@SuppressLint("NewApi")

fun main() {
    println("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return

    val mainList = nAndPhoneNumber(n).toList()
    mainList.forEach { println(it) }

    println("Количество уникальных номеров в списке: ${mainList.toSet().size}")
    println("Сумма длинн всех номеров: ${mainList.sumBy { it?.length ?: 0 }}")

    val name = mutableListOf<String?>()
    val mutableMap = mutableMapOf<String?, String?>()

    for (current in 0 until mainList.size) {
        println("Введите имя человека с номером телефона ${mainList[current]}")
        name.add(readLine() ?: return)
        mutableMap.put(name[current], mainList[current])
    }
    mutableMap.toSortedMap(compareBy<String?> {it?.length}.thenBy { it })
    mutableMap.forEach { key, value -> println("Человек $key  Номер телефона $value") }
}

fun nAndPhoneNumber(n: Int): List<String?> {
    val mutableList = mutableListOf<String?>()
    for (current in 1..n) {
        println("Введите номер телефона: ")
        val phoneNumber: CharSequence = readLine().toString()
        if (!Pattern.matches("(\\\\+\\\\d( )?)?([-\\\\( ]\\\\d{3}[-\\\\) ])( )?\\\\d{3}-\\\\d{4}", phoneNumber)) {
            phoneNumber.length > 10 && phoneNumber.length <= 12}
        mutableList.add(phoneNumber.toString())
    }
    return mutableList.filter { it?.startsWith("+7") ?: return emptyList() }
}