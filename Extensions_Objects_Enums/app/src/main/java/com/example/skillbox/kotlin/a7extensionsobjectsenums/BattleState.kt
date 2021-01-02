package com.example.skillbox.kotlin.a7extensionsobjectsenums

sealed class BattleState {
    data class Progress(
            val teamHealth: Int
    ) : BattleState() {
        init {
            println("TeamHealth of first and second team is: $teamHealth")
        }
    }

    object FirstTeamWin : BattleState() {
        init {
            println("First team win")
        }
    }

    object SecondTeamWin : BattleState() {
        init {
            println("Second team win")
        }
    }

    object Draw : BattleState() {
        init {
            println("All warriors is die. Draw")
        }
    }
}