package com.example.skillbox.kotlin.a7extensionsobjectsenums

class Battle(
        firstTeamSize: Int,
        secondTeamSize: Int
) {
    private val firstTeam: Team = Team(firstTeamSize)
    val firstTeamList: List<Warrior> = firstTeam.createTeam
    private val secondTeam: Team = Team(secondTeamSize)
    val secondTeamList: List<Warrior> = secondTeam.createTeam
    val battleIsOver: Boolean
        get() {
            return ((firstTeamList.all { it.isKilled } || (secondTeamList.all { it.isKilled })))
        }

    private fun getBattleState(): BattleState {
        return if (firstTeamList.any { !it.isKilled } && (secondTeamList.any { !it.isKilled })) {
            val teamHealth: Int = firstTeamList.sumBy { it.currentHealth } + secondTeamList.sumBy { it.currentHealth }
            BattleState.Progress(teamHealth)
        } else if (firstTeamList.any { !it.isKilled } && (secondTeamList.all { it.isKilled })) {
            BattleState.FirstTeamWin
        } else if (firstTeamList.all { it.isKilled } && (secondTeamList.any { !it.isKilled })) {
            BattleState.SecondTeamWin
        } else BattleState.Draw
    }

    fun nextBattleIteration() {
        while (!battleIsOver) {
            var n = -1
            n++
            firstTeamList.shuffled()
            secondTeamList.shuffled()

            if (firstTeamList.size == secondTeamList.size) {
                if (!firstTeamList[n].isKilled) {
                    for (current in 0 until firstTeamList.size) {
                        firstTeamList[current].attack(secondTeamList[current])
                        continue
                    }
                }
                if (!secondTeamList[n].isKilled) {
                    for (current in 0 until firstTeamList.size) {
                        secondTeamList[current].attack(firstTeamList[current])
                        continue
                    }
                }
                getBattleState()

            } else if (firstTeamList.size < secondTeamList.size) {
                if (!firstTeamList[n].isKilled) {
                    for (current in 0 until firstTeamList.size) {
                        firstTeamList[current].attack(secondTeamList[current])
                        continue
                    }
                }
                if (!secondTeamList[n].isKilled) {
                    for (current in 0 until firstTeamList.size) {
                        secondTeamList[current].attack(firstTeamList[current])
                        continue
                    }
                }
                getBattleState()

            } else {
                if (!secondTeamList[n].isKilled) {
                    for (current in 0 until secondTeamList.size) {
                        secondTeamList[current].attack(firstTeamList[current])
                        continue
                    }
                }
                if (!firstTeamList[n].isKilled) {
                    for (current in 0 until secondTeamList.size) {
                        firstTeamList[current].attack(secondTeamList[current])
                        continue
                    }
                }
                getBattleState()
            }
        }
    }
}

