package com.example.skillbox.kotlin.a7extensionsobjectsenums

fun main() {
    var firstTeamWinCount = 0
    var secondTeamWinCount = 0
    var drawCount = 0

    println("Enter the number of iterations:")
    val numberOfIteration: Int = readLine()?.toIntOrNull() ?: return

    for (current in 1..numberOfIteration) {
        println("Enter first team size:")
        val firstTeamSize: Int = readLine()?.toIntOrNull() ?: return

        println("Enter second team size:")
        val secondTeamSize: Int = readLine()?.toIntOrNull() ?: return

        val battle = Battle(firstTeamSize = firstTeamSize, secondTeamSize = secondTeamSize)

        while (!battle.isOver) {
            battle.nextBattleIteration()
            battle.getBattleState().printBattleState()
            when {
                battle.getBattleState() is BattleState.FirstTeamWin -> firstTeamWinCount++
                battle.getBattleState() is BattleState.SecondTeamWin -> secondTeamWinCount++
                battle.getBattleState() is BattleState.Draw -> drawCount++
            }
        }
    }
    println("""
            ---
            firstTeam win: $firstTeamWinCount
            secondTeam win: $secondTeamWinCount
            draw: $drawCount
        """.trimIndent())
}
