package com.example.skillbox.kotlin.loopshome

fun main() {
    println(maxInt(15,18))
    println(calculatePrice(false))
    println(getCar(290,true))
    println(getDevelopPost(5))
    println(getDevelopPost(-1))
    println(getDevelopPost(0))
    println(getDevelopPost(2))
    println(getDevelopPost(3))
}
fun maxInt (a:Int, b:Int): Int {
    val maxValue: Int
    if (a > b) {
        maxValue=a } else {
        maxValue = b
    }
    return maxValue
}
fun calculatePrice(booleanParam: Boolean): Int {
    return if (booleanParam) {
        val intermidateResult: Int = 234 + 432
        intermidateResult +5
    } else {
        val intermidateResult: Int = 2 + 5
        intermidateResult +5
    }
}
fun getCar(maxSpeed: Int, hasSportMode: Boolean): String {
    return when {
        maxSpeed < 20 -> {
            "Трактор"
        }
        maxSpeed < 60 -> {
            "Медленная машина"
        }
        maxSpeed <= 200 || hasSportMode -> {
            "Обычная машина"
        }
        else -> {
            "Быстрая машина"
        }
    }
}
fun getDevelopPost(experience: Int): String {
return if (experience<0) {
    "Incorrect"
} else {
    when (experience) {
0 -> "Intern"
        in 1..2 -> "Junior"
        in 3..4 -> "Middle"
        in 5..6 -> "Senior"
        else -> "Guru"
    }
}

}