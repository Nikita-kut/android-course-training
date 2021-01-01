package com.example.skillbox.kotlin.a7extensionsobjectsenums

class Battle(
) {
    val firstTeam: Team = Team()
    val secondTeam: Team = Team()
    val battleIsOver: Boolean = false

    fun getBattleState(): BattleState {
        if (firstTeam.team.forEach { it.isKilled } == ) {

        }

        return BattleState.Progress()
    }
}