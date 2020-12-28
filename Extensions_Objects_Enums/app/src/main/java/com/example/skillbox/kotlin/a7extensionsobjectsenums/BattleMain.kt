package com.example.skillbox.kotlin.a7extensionsobjectsenums

fun main() {
    val automaton: AbstractWeapon = Weapons.createAutomaton()
    automaton.recharge()
    automaton.getAmmoForShot().forEach { println(it) }

}