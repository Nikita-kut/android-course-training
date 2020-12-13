package com.example.skillbox.kotlin.classes.home

class Bird(name: String, energy: Int, weight: Int, maxAge: Int): Animal(name, energy, weight, maxAge), Soundable {

    override fun move() {
        super.move()
        println("Flying")
    }

    override fun makeChild(): Bird {
        val newEnergy = (1..100).random()
        val newWeight = (1..5).random()
        val newBird = Bird(name, newEnergy, newWeight, maxAge)
        println("$name is reborn, maxAge = $maxAge, energy = $newEnergy, weight = $newWeight")
        return newBird
    }

    override fun makeSound() {
        println("Chirp-chirp")
    }
}