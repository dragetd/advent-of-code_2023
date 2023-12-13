package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution
import kotlin.math.pow


class Day04 : DaySolution {

    val numberRegex = """Card \d+: ([\d ]+)|([\d ]+)""".toRegex()

    override fun solve1(input: String): String {
        return input.lines().sumOf { line ->
            points(line.numberOfWinningsPicks())
        }.toString()
    }

    override fun solve2(input: String): String {
        TODO("Not yet implemented")
    }

    private fun String.numberOfWinningsPicks(): Int {
        val match = numberRegex.matchEntire(this) ?: return 0
        val (group) = match.destructured
        val numbers = group.split(" ")
        return numbers.filter { it in numbers }.count()
    }

    fun points(x: Int) = if (x == 0) 0 else 2.0.pow(x - 1).toInt()
}

