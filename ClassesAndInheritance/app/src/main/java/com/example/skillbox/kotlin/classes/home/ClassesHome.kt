package com.example.skillbox.kotlin.classes.home

fun main() {
    val zooList = Zoo ("ZooList", 1,1,1)

    for (current in 1..5) {

        zooList.zooList.forEach {
            it.eat()
            it.sleep()
            it.move()
            it.makeChild()
            it.makeSound()
        }
    }


}