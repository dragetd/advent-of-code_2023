package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution


object Day01 : DaySolution {

    @JvmStatic
    fun main(args: Array<String>) {
        """1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet""".trimIndent().printSolve1()
    }

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
