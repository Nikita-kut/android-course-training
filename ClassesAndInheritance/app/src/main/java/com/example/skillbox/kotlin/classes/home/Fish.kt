package com.example.skillbox.kotlin.classes.home

class Fish(name: String, energy: Int, weight: Int, maxAge: Int) : Animal(name, energy, weight, maxAge) {

    override fun move() {
        super.move()
        println("Floating")
    }

    override fun makeChild(): Fish {
        val newEnergy = (1..100).random()
        val newWeight = (1..5).random()
        val newFish = Fish(name, newEnergy, newWeight, maxAge)
        println("$name is reborn, maxAge = $maxAge, energy = $newEnergy, weight = $newWeight")
        return newFish
    }
}