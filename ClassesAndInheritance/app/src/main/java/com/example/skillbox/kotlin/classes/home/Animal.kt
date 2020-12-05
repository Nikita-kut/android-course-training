package com.example.skillbox.kotlin.classes.home

import kotlin.random.Random

class Animal constructor(
        val name: String,
        energy: Int,
        weight: Int,
        private val maxAge: Int
) {
    var energy = energy
        private set

    val needEnergy: Int = 5


    var weight = weight
        private set

    var age = 0
        private set

    var isTooOld: Boolean = if (age <= maxAge) true else false

    fun sleep() {
        if (energy >= needEnergy) {
            energy -= 5
            age -= 1
            println("$name is sleep")
        } else return
    }

    private fun incrementAgeSometimes() {
        if (Random.nextBoolean()) age += 1
    }

    fun eat() {
        if (energy <= needEnergy) {
            energy += 3
            weight += 1
            incrementAgeSometimes()
            println("$name is eat")
        } else return
    }

    fun move() {
        if (energy >= needEnergy) {
            energy -= 5
            weight -= 1
            incrementAgeSometimes()
            "$name is move"
        } else return
    }

    fun makeChild() {
        energy = (1..100).random()
        weight = (1..5).random()
        println("$name is reborn, maxAge = $maxAge, energy = $energy, weight = $weight")
        return
    }
}

