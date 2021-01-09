package com.example.skillbox.kotlin.a7extensionsobjectsenums

import kotlin.random.Random

class Team(teamSize: Int) {

    var warriors: List<Warrior> = mutableListOf()

    init {
        this.warriors = createTeam(teamSize)
    }

    private fun createTeam(teamSize: Int): List<Warrior> {

        for (current in 1..teamSize) {
            when (Random.nextInt(from = 1, until = 101)) {
                in 1..10 -> (warriors as MutableList<Warrior>).add(Major())
                in 11..30 -> (warriors as MutableList<Warrior>).add(Captain())
                in 31..60 -> (warriors as MutableList<Warrior>).add(Corporal())
                in 61..100 -> (warriors as MutableList<Warrior>).add(Soldier())
            }
        }
        return warriors
    }

    override fun toString(): String {
        return "Team(team=$warriors)"
    }
}
