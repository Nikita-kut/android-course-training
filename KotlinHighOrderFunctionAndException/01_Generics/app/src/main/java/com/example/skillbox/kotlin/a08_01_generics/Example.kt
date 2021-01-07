package com.example.skillbox.kotlin.a08_01_generics

fun main() {
//    val objectContest = Contest<String>()
//
//    objectContest.scores.forEach { println(it) }
//    objectContest.addScore("Fuck1", 1)
//    objectContest.addScore("Fuck2", 2)
//    objectContest.addScore("Fuck3", 3)
//    objectContest.scores.forEach { println(it) }
//    objectContest.getWinners().forEach { println(it) }

    val objectContest = Contest<Pets>()

    objectContest.scores.forEach { println(it) }
    objectContest.addScore(Fish("Zumba"), 1)
    objectContest.addScore(Dog ("Doggie"), 2)
    objectContest.addScore(Cat("Cattie"), 3)
    objectContest.scores.forEach { println(it) }
    objectContest.getWinners().forEach { println(it) }

}

abstract class Pets(var name: String) {
    override fun toString(): String {
        return "Pets(name='$name')"
    }
}

class Cat(name: String) : Pets(name)
class Dog(name: String) : Pets(name)
class Fish(name: String) : Pets(name)

class Contest<T: Pets> {
    val scores: MutableMap<T, Int> = mutableMapOf()

    fun addScore(t: T, score: Int) {
        if (score >= 0) scores.put(t, score)
    }

    fun getWinners(): MutableSet<T> {
        val highScore = scores.values.max()
        val winners = mutableSetOf<T>()
        for ((t, score) in scores) {
            if (score == highScore) winners.add(t)
        }
        return winners
    }
}