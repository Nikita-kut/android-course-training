package com.example.skillbox.kotlin.a7extensionsobjectsenums

sealed class BattleState : PrintBattleState {

    data class Progress(
            val teamHealth: Int
    ) : BattleState() {
        override fun printBattleState() {
            println("TeamHealth of first and second team is: $teamHealth")
        }
    }

    object FirstTeamWin : BattleState() {
        override fun printBattleState() {
            println("First team win")
        }
    }

    object SecondTeamWin : BattleState() {
        override fun printBattleState() {
            println("Second team win")
        }
    }

    object Draw : BattleState() {
        override fun printBattleState() {
            println("All warriors is die. Draw")
        }
    }
}