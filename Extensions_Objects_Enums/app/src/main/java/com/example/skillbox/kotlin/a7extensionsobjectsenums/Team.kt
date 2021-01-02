package com.example.skillbox.kotlin.a7extensionsobjectsenums

import kotlin.random.Random

class Team(teamSize: Int) {

    var createTeam: MutableList<Warrior> = mutableListOf()

    init {
        this.createTeam = createTeam(teamSize)
    }

    private fun createTeam(teamSize: Int): MutableList<Warrior> {

        for (current in 1..teamSize) {
            when (Random.nextInt(from = 1, until = 101)) {
                in 1..10 -> createTeam.add(Major())
                in 11..30 -> createTeam.add(Captain())
                in 31..60 -> createTeam.add(Corporal())
                in 61..100 -> createTeam.add(Soldier())
            }
        }
        return createTeam
    }

    override fun toString(): String {
        return "Team(team=$createTeam)"
    }
}