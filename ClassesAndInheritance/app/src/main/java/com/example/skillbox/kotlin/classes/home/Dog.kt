package com.example.skillbox.kotlin.classes.home

class Dog(name: String, energy: Int, weight: Int, maxAge: Int) : Animal(name, energy, weight, maxAge) {

    override fun move() {
        super.move()
        println("Running")
    }

    override fun makeChild(): Dog {
        val newEnergy = (1..100).random()
        val newWeight = (1..5).random()
        val newDog = Dog(name, newEnergy, newWeight, maxAge)
        println("$name is reborn, maxAge = $maxAge, energy = $newEnergy, weight = $newWeight")
        return newDog
    }

    override fun makeSound() {
        println("Woof-woof")
    }
}