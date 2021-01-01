package com.example.skillbox.kotlin.a7extensionsobjectsenums

import kotlin.random.Random

class Team {
    val team: MutableList<Warrior> = mutableListOf()

    fun createTeam(teamSize: Int): MutableList<Warrior> {

        for (current in 1..teamSize) {
            when (Random.nextInt(1, 101)) {
                in 1..10 -> team.add(Major())
                in 11..30 -> team.add(Captain())
                in 31..60 -> team.add(Corporal())
                in 61..100 -> team.add(Soldier())
            }
        }
        return team
    }

    override fun toString(): String {
        return "Team(team=$team)"
    }


}