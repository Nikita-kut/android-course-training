package com.example.skillbox.kotlin.classes.home

class Bird(name: String, energy: Int, weight: Int, maxAge: Int): Animal(name, energy, weight, maxAge) {

    override fun move() {
        super.move()
        println("Fly")
    }

    override fun makeChild(): Bird {
        return super.makeChild() as Bird
    }
}