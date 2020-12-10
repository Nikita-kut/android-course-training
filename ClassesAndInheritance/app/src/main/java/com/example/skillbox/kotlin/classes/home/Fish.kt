package com.example.skillbox.kotlin.classes.home

class Fish(name: String, energy: Int, weight: Int, maxAge: Int): Animal(name, energy, weight, maxAge) {

    override fun move() {
        super.move()
        println("Float")
    }

    override fun makeChild(): Fish {
        return super.makeChild() as Fish
    }
}