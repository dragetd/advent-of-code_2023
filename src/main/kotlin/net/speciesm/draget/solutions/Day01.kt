package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution


class Day01 : DaySolution {
object Day01 : DaySolution {

    override fun solve1(input: String): String {
        return input.lines().sumOf {
            val first = it.first() { it.isDigit() }
            val last = it.last() { it.isDigit() }
            "$first$last".toLong()
        }.toString()

    }

    override fun solve2(input: String): String {
        TODO("Not yet implemented")
    }
}
