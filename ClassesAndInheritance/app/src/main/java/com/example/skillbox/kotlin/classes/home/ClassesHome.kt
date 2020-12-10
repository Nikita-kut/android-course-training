package com.example.skillbox.kotlin.classes.home

fun main() {
    var animal = Animal("Animal", 15, 5, 5)
    while (!animal.isTooOld) {
        animal.eat()
        animal.sleep()
        animal.move()
        if (animal.isTooOld) {
            animal = animal.makeChild()
        }
    }
}