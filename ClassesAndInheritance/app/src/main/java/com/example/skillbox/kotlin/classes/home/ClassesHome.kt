package com.example.skillbox.kotlin.classes.home

fun main() {
    val animal = Animal("Doggie", 15, 5, 5)

    with(animal) {
        while (isTooOld) {
            eat()
            sleep()
            move()
            if (!isTooOld) {
                makeChild()
            }
        }
    }
}
