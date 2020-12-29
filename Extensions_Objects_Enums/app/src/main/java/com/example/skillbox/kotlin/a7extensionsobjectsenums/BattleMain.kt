package com.example.skillbox.kotlin.a7extensionsobjectsenums

fun main() {
    val automaton: AbstractWeapon = Weapons.createAutomaton()

    automaton.recharge()
    println("Automaton size is ${automaton.currentListAmmo.size}")
    println(automaton.isAmmo)

    automaton.getAmmoForShot()
    println("Automaton size1 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size2 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size3 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size4 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size5 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size6 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size6 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size6 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size6 is ${automaton.currentListAmmo.size}")
    automaton.getAmmoForShot()
    println("Automaton size6 is ${automaton.currentListAmmo.size}")
    println(automaton.isAmmo)
}