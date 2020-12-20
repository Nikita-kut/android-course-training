package com.example.skillbox.kotlin.a6basemerodsandproperty

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun main() {
    val person1 = Person(100, 50, "Person1")
    val person2 = Person(100, 50, "Person1")

    val hashSet = hashSetOf(person1, person2)
    hashSet.add(Person(120, 70, "Person2"))
    println("hashSet size is ${hashSet.size}")
    hashSet.forEach { println(it) }

    hashSet.forEach { it.buyPet() }
}

class PrintNameOfPerson<T>(
        var pets: T
) : ReadOnlyProperty<Person, T> {
    override fun getValue(thisRef: Person, property: KProperty<*>): T {
        println("Person name: ${thisRef.name} and HashSet of pets: $pets")
        return pets
    }
}
