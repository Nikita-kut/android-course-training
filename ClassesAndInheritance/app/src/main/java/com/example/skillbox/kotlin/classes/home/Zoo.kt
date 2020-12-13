package com.example.skillbox.kotlin.classes.home

class Zoo (name: String, energy: Int, weight: Int, maxAge: Int): Animal(name, energy, weight, maxAge) {

    override fun makeSound() {}

    val bird1: Bird = Bird("Bird1", 5,5,10)
    val bird2: Bird = Bird("Bird2", 5,5,10)
    val bird3: Bird = Bird("Bird3", 5,5,10)
    val bird4: Bird = Bird("Bird4", 5,5,10)
    val bird5: Bird = Bird("Bird5", 5,5,10)

    val fish1: Fish = Fish("Fish1", 10, 7, 8)
    val fish2: Fish = Fish("Fish2", 10, 7, 8)
    val fish3: Fish = Fish("Fish3", 10, 7, 8)

    val dog1: Dog = Dog("Dog1", 15,9,11)
    val dog2: Dog = Dog("Dog2", 15,9,11)

    var zooList = listOf<Animal>(
            bird1,
            bird2,
            bird3,
            bird4,
            bird5,
            fish1,
            fish2,
            fish3,
            dog1,
            dog2,
            makeChild(),
            makeChild()
    )
}