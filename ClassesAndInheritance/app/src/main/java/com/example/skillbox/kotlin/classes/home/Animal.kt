package com.example.skillbox.kotlin.classes.home

import kotlin.random.Random

abstract class Animal constructor(
        val name: String,
        energy: Int,
        weight: Int,
        maxAge: Int
) : AgedAnimal(maxAge) {

    var energy = energy
        private set

    var weight = weight
        private set

    var age = 0
        private set

    val isTooOld: Boolean
        get() = age >= maxAge

    fun sleep() {
        if (!isTooOld) {
            energy += 5
            age += 1
            println("$name is sleep")
        }
    }

    private fun incrementAgeSometimes() {
        if (Random.nextBoolean()) age += 1
    }

    fun eat() {
        if (!isTooOld) {
            energy += 3
            weight += 1
            incrementAgeSometimes()
            println("$name is eat")
        }
    }

    open fun move() {
        if (energy >= 5 && weight > 0 && !isTooOld) {
            energy -= 5
            weight -= 1
            incrementAgeSometimes()
            println("$name is move")
        }
    }

    open fun makeChild(): Animal {
        val newEnergy = (1..100).random()
        val newWeight = (1..5).random()
        val newAnimal = object: Animal(name, newEnergy, newWeight, this.maxAge) {
            override fun makeSound() {}
        }
        println("$name is reborn, maxAge = $maxAge, energy = $newEnergy, weight = $newWeight")
        return newAnimal
    }
}


