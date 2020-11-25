package com.example.skillbox.kotlin.collecthome

fun main() {
    println("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return

    val mainList: MutableList<String?> = nAndPhoneNumber(n) as MutableList<String?>
    mainList.forEach { println(it) }

    println("Количество уникальных номеров в списке: ${mainList.toSet().size}")
    println("Сумма длинн всех номеров: ${mainList.sumBy { it!!.length }}")

    var name: MutableList<String?> = emptyList<String?>().toMutableList()
    for (current2 in 1..mainList.size) {
        println("Введите имя человека с номером телефона ${mainList[{current2}]}")
        name = readLine()
    }

    val mutableMap = mutableMapOf(
            "Человек: ${name[0]}" to "Номер телефона: ${mainList[0]}"
    )
    mutableMap.forEach { println(it) }
}

fun nAndPhoneNumber(n: Int): List<String?> {
    val mutableList = emptyList<String?>().toMutableList()
    for (current in 1..n) {
        println("Введите номер телефона: ")
        val phoneNumber: String? = readLine()
        mutableList.add(phoneNumber)
    }
    return mutableList.filter { it!!.startsWith("+7") }
}


