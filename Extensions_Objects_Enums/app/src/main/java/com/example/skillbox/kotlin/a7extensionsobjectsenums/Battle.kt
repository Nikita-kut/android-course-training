package com.example.skillbox.kotlin.a7extensionsobjectsenums

class Battle(
        val firstTeam: Team,
        val secondTeam: Team,
        val battleIsOver: Boolean
) {

    fun getBattleState(): BattleState {


        return BattleState.Progress()
    }
}