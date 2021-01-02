package com.example.skillbox.kotlin.a7extensionsobjectsenums

fun main() {
    println("Enter first team size")
    val firstTeamSize: Int = readLine()?.toIntOrNull() ?: return

    println("Enter second team size")
    val secondTeamSize: Int = readLine()?.toIntOrNull() ?: return

    val battle: Battle = Battle(firstTeamSize = firstTeamSize, secondTeamSize = secondTeamSize)

    battle.nextBattleIteration()
}