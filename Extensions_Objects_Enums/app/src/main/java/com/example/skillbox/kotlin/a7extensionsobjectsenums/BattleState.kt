package com.example.skillbox.kotlin.a7extensionsobjectsenums

sealed class BattleState {
    data class Progress(
            val teamHealth: Int
    ) : BattleState() {
    }

    class FirstTeamWin : BattleState()

    class SecondTeamWin : BattleState()

    class Draw : BattleState()
}