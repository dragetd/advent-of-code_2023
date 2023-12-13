package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution


class Day03 : DaySolution {

    data class Number(val number: Long, val x: Int, val y: Int)

    val numberRegex = "[0-9]+".toRegex()
    val symbolRegex = "[^.\\d]".toRegex()

    override fun solve1(input: String): String = input.getNumbers()
        .filter { it.hasSymbolAround(input) }
        .sumOf { it.number }
        .toString()

    override fun solve2(input: String): String {
        TODO("Not yet implemented")
    }

    fun String.getNumbers(): List<Number> {
        return this.lines().flatMapIndexed { y, line ->
            numberRegex.findAll(line).map {
                Number(it.value.toLong(), it.range.first, y)
            }
        }
    }

    fun Number.hasSymbolAround(input: String): Boolean {
        val (maxWidthIndex, maxHeightIndex) = with(input.lines()) { first().length - 1 to size - 1 }
        val (x1, y1) = maxOf(x - 1, 0) to maxOf(y - 1, 0)
        val (x2, y2) = minOf(x + this.number.toString().length, maxWidthIndex) to minOf(y + 1, maxHeightIndex)

        return (y1..y2).any { y ->
            (x1..x2).any { x ->
                input.at(x, y).isSymbol()
            }
        }
    }

    private fun String.at(x: Int, y: Int): Char {
        return this.lines()[y][x]
    }

    private fun Char.isSymbol(): Boolean = symbolRegex.matches("$this")
}
