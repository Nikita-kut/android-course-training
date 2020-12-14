package com.example.skillbox.kotlin.classes.home

import kotlin.random.Random

class Zoo() {

    fun makeZooList(): MutableList<Animal> {
        val zooList = mutableListOf<Animal>()
        zooList.addAll(List(5) { Bird("Bird $it", 5, 5, 10) })
        zooList.addAll(List(3) { Fish("Fish $it", 10, 7, 8) })
        zooList.addAll(List(2) { Dog("Dog $it", 15, 9, 11) })
        zooList.addAll(List(2) { object : Animal("Animal $it", 10, 6, 5) {} })
        return zooList
    }

    fun randomDo(makeZooList: MutableList<Animal>) {
        println("Start zoo size is ${makeZooList.size}")
        val deathAnimal = mutableListOf<Animal>()
        for (current in 1..5) {
            makeZooList().forEach {
                when (if (it is Soundable) Random.nextInt(1, 6) else Random.nextInt(1, 5)) {
                    1 -> it.eat()
                    2 -> it.sleep()
                    3 -> it.move()
                    4 -> makeZooList.add(it.makeChild())
                    5 -> if (it is Soundable) it.makeSound()
                }
                if (makeZooList.size == 0) return println("Zoo is die")
                if (it.isTooOld) deathAnimal.add(it)
            }
        }
        println("Zoo size is ${makeZooList.size}")
        println("DeathAnimal size is ${deathAnimal.size}")
        makeZooList.removeAll (deathAnimal)
        println("Final zoo size is ${makeZooList.size}")
    }
}