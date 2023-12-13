package net.speciesm.draget.solutions

import net.speciesm.draget.utils.AoCSolutionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Test {

    val input = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent()

    val cut = AoCSolutionRepository.findByDay(AoCSolutionRepository.dayByClass(this.javaClass))

    @Test
    fun solution1() {
        assertThat(cut.solve1(input)).isEqualTo("374")
    }

    @Test
    fun solution2() {
        val tempCut = cut as Day11
        assertThat(tempCut.solve2WithExpand(input, 10)).isEqualTo("1030")
        assertThat(tempCut.solve2WithExpand(input, 100)).isEqualTo("8410")
    }
}
