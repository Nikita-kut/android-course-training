package com.example.skillbox.kotlin.a08_02_highorderfunctions

fun main() {

//    val lambda = { println("Text from lambda") }
//    lambda()
//
//    val lambdaWithParam = { x: Int -> println("X from lambda: $x") }
//    lambdaWithParam(5)
//
//    val sumLambda = { x: Int, y: Int -> x + y }
//    println("Sum x and y = ${sumLambda(30, 100)}")

    val users = listOf(
        User("Vasya", "Sergeev", 30),
        User("Vasya", "Ivanov", 10),
        User("Vasya", "Petrov", 20),
        User("Vasya", "Serov", 15),
        User("Vasya", "Sidorov", 25)
    )

//    var oldestUser: User? = null
//    for (user in users) {
//        val currentAge = user.age
//        val maxAge = oldestUser?.age ?: Int.MIN_VALUE
//        if (currentAge > maxAge) {
//            oldestUser = user
//        }
//    }
    val user1 = users[0]
    users.maxBy { user: User -> user.age.let { it + 1 } }
    val maxNameUser1 = users.maxBy { it.getFullNameLenght() }
    val maxNameUser2 = users.maxBy(User::getFullNameLenght)
    println(maxNameUser1)
    println(maxNameUser2)

    makeCalculations({ println("Result = $it") }, { })
}

inline fun makeCalculations(callback: (Int) -> Unit, noinline callback2: (Int) -> Unit) {
    val value = 1 + 1
    callback(value)

}
