package net.speciesm.draget

import net.speciesm.draget.utils.AoCSolutionRepository

interface DaySolution {
    fun solve1(input: String): String
    fun solve2(input: String): String

    fun getDay(): Int {
        return AoCSolutionRepository.dayByClass(this.javaClass)
    }
}
