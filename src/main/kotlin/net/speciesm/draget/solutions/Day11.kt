package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day11 : DaySolution {

    data class Galaxy(var x: Int, var y: Int) {
        fun distanceTo(galaxy: Galaxy, expandedXIndices: List<Int>, expandedYIndices: List<Int>, expandFactor: Int = 2): Long {
            val xDistance = abs(this.x - galaxy.x)
            val yDistance = abs(this.y - galaxy.y)

            val xExpands = expandedXIndices.between(this.x, galaxy.x)
            val yExpands = expandedYIndices.between(this.y, galaxy.y)

            val totalXDistance: Long = xDistance.toLong() - xExpands + (xExpands * expandFactor)
            val totalYDistance: Long = yDistance.toLong() - yExpands + (yExpands * expandFactor)

            return totalXDistance + totalYDistance
        }


        fun List<Int>.between(v1: Int, v2: Int): Int =
            this.count { it in min(v1, v2)..max(v1, v2) }
    }

    override fun solve1(input: String): String {
        val galaxies = input.toGalaxies()

        val expandedXIndicies = input.toExpandedXIndices()
        val expandedYIndicies = input.toExpandedYIndices()
        val maxIndex = galaxies.size - 1
        var sumDistance = 0L
        for (i in 0..maxIndex) {
            for (j in i + 1..maxIndex) {
                sumDistance += galaxies[i].distanceTo(galaxies[j], expandedXIndicies, expandedYIndicies)
            }

        }
        return sumDistance.toString()
    }

    fun String.toExpandedXIndices(): List<Int> {
        return lines().first().indices.asSequence()
            .filter { char -> lines().none { it[char] == '#' } }
            .toList()
    }

    fun String.toExpandedYIndices(): List<Int> {
        return this.lines().mapIndexedNotNull { y, line ->
            if (line.contains('#')) null else y
        }
    }

    fun String.toGalaxies(): List<Galaxy> {
        return this.lines()
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, char ->
                    if (char == '#') Galaxy(x, y) else null
                }
            }
    }

    fun solve2WithExpand(input: String, expandFactor: Int): String {
        val galaxies = input.toGalaxies()

        val expandedXIndices = input.toExpandedXIndices()
        val expandedYIndices = input.toExpandedYIndices()
        val maxIndex = galaxies.size - 1
        var sumDistance = 0L
        for (i in 0..maxIndex) {
            for (j in i + 1..maxIndex) {
                sumDistance += galaxies[i].distanceTo(galaxies[j], expandedXIndices, expandedYIndices, expandFactor)
            }

        }
        return sumDistance.toString()
    }

    override fun solve2(input: String): String = solve2WithExpand(input, 1000000)
}

