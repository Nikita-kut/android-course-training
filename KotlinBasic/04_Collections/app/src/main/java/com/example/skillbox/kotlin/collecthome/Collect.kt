package com.example.skillbox.kotlin.collecthome

fun main() {
    println("Введите число: ")
    val n = readLine()?.toIntOrNull() ?: return

    val mainList: MutableList<String?> = nAndPhoneNumber(n) as MutableList<String?>
    mainList.forEach { println(it) }

    println("Количество уникальных номеров в списке: ${mainList.toSet().size}")
    println("Сумма длинн всех номеров: ${mainList.sumBy { it!!.length }}")

    val name: MutableList<String?> = emptyList<String?>().toMutableList()
    val mutableMap = mutableMapOf<String, String>()
    
    for (current in 0 until mainList.size) {
        println("Введите имя человека с номером телефона ${mainList[current]}")
        name.add(readLine())
    }
    mutableMap.put("$mainList", "$name")
    mutableMap.forEach {
        println("Человек: $name. Номер телефона: $mainList")
    }

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


