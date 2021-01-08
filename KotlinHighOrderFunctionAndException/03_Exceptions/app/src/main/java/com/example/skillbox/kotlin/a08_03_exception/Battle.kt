package com.example.skillbox.kotlin.a7extensionsobjectsenums

class Battle(
        firstTeamSize: Int,
        secondTeamSize: Int
) {
    private val firstTeam: Team = Team(firstTeamSize)
    private val secondTeam: Team = Team(secondTeamSize)
    val isOver: Boolean
        get() {
            return ((firstTeam.warriors.all { it.isKilled } || (secondTeam.warriors.all { it.isKilled })))
        }

    fun getBattleState(): BattleState {
        val teamHealth: Int = firstTeam.warriors.sumBy { it.currentHealth } + secondTeam.warriors.sumBy { it.currentHealth }
        return when {
            firstTeam.warriors.any { !it.isKilled } && (secondTeam.warriors.any { !it.isKilled }) -> BattleState.Progress(teamHealth)
            firstTeam.warriors.any { !it.isKilled } && (secondTeam.warriors.all { it.isKilled }) -> BattleState.FirstTeamWin
            firstTeam.warriors.all { it.isKilled } && (secondTeam.warriors.any { !it.isKilled }) -> BattleState.SecondTeamWin
            else -> BattleState.Draw
        }
    }

    fun nextBattleIteration() {
        val firstShuffledTeam = firstTeam.warriors.shuffled()
        val secondShuffledTeam = secondTeam.warriors.shuffled()

        if (firstShuffledTeam.size <= secondShuffledTeam.size) {
            firstShuffledTeam.forEachIndexed { index, it ->
                if (!it.isKilled && !secondShuffledTeam[index].isKilled) {
                    it.attack(secondShuffledTeam[index])
                    secondShuffledTeam[index].attack(it)
                }
            }
        } else {
            secondShuffledTeam.forEachIndexed { index, it ->
                if (!it.isKilled && !firstShuffledTeam[index].isKilled) {
                    it.attack(firstShuffledTeam[index])
                    firstShuffledTeam[index].attack(it)
                }
            }
        }
    }
}
