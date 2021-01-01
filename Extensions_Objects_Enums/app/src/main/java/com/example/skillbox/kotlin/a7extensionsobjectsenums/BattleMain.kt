package com.example.skillbox.kotlin.a7extensionsobjectsenums

fun main() {
    val automaton: AbstractWeapon = Weapons.createAutomaton()

    val corporal: Corporal = Corporal()
    val soldier: Soldier = Soldier()
    println(soldier.currentHealth)

    corporal.attack(soldier)
    corporal.attack(soldier)

    println(soldier.currentHealth)

   val team = Team ()

    val team2: MutableList<Warrior> = team.createTeam(teamSize = 10)

    team2.forEach { println(it) }
    println(team2.size)


}