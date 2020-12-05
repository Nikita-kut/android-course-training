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

    var weight = weight
        private set

    var age = 0
        private set

    val isTooOld: Boolean
        get() = age <= maxAge

    fun sleep() {
        if (energy <= 5) {
            energy += 5
            age += 1
            println("$name is sleep")
        }
    }

    private fun incrementAgeSometimes() {
        if (Random.nextBoolean()) age += 1
    }

    fun eat() {
        if (energy <= 5) {
            energy += 3
            weight += 1
            incrementAgeSometimes()
            println("$name is eat")
        }
    }

    fun move() {
        if (energy >= 5 && weight > 0) {
            energy -= 5
            weight -= 1
            incrementAgeSometimes()
            println("$name is move")
        }
    }

    fun makeChild() {
        energy = (1..100).random()
        weight = (1..5).random()
        println("$name is reborn, maxAge = $maxAge, energy = $energy, weight = $weight")
    }
}


