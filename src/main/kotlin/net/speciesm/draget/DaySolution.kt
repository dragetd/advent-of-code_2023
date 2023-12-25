package net.speciesm.draget

import net.speciesm.draget.utils.AoCSolutionRepository

val summaryLength = 10
private fun ellipse(text: String): String {
    val lines = text.lines()
    return when {
        lines.size > 1 -> lines.first().take(summaryLength - 1) + "…"
        lines.first().length > summaryLength -> lines.first().take(summaryLength - 1) + "…"
        else -> lines.first()
    }
}

interface DaySolution {

    fun String.printSolve1() {
        println("Solve1 on input \"${ellipse(this)}\": ${solve1(this)}")
    }

    fun String.printSolve2() {
        println("Solve2 on input \"${ellipse(this)}\": ${solve2(this)}")
    }

    fun solve1(input: String): String
    fun solve2(input: String): String

    fun getDay(): Int {
        return AoCSolutionRepository.dayByClass(this.javaClass)
    }
}
