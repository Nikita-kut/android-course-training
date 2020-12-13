package com.example.skillbox.kotlin.classes.home

class Zoo(name: String, energy: Int, weight: Int, maxAge: Int) : Animal(name, energy, weight, maxAge) {

    fun makeZooList(): List<Animal> {
        val zooList = listOf<Animal>().toMutableList()
        zooList.addAll(List(5) { Bird("Name $it", 5, 5, 10) })
        zooList.addAll(List(3) { Fish("Fish $it", 10, 7, 8) })
        zooList.addAll(List(2) { Dog("Dog $it", 15, 9, 11) })
        zooList.addAll(List(2) { makeChild() })
        return zooList
    }

}