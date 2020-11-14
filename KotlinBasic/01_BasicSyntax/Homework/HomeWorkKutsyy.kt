package com.example.skillbox.kotlin.basic

fun main() {
    val firstName = "Nikita"
    val lastName = "Kutsyy"
    var height: Int = 178
    val weight: Float = 73.8f

    var isChild: Boolean = false
    if (height<150||weight<40) {
        isChild = true
    }

    val info = """
        Name: $firstName
        Last Name: $lastName
        Height: $height
        Weight: $weight
        He is: ${if (isChild) "Child" else "Adult"}
    """.trimIndent()
    println(info)
    height = 190
    println(height)
}