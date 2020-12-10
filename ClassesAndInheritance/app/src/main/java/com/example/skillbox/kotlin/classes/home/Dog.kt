package com.example.skillbox.kotlin.classes.home

class Dog(name: String, energy: Int, weight: Int, maxAge: Int): Animal(name, energy, weight, maxAge) {

    override fun move() {
        super.move()
        println("Run")
    }

    override fun makeChild(): Dog {
        return super.makeChild() as Dog
    }
}