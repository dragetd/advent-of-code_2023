package net.speciesm.draget.solutions

import net.speciesm.draget.utils.AoCSolutionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day14Test {

    private val input = """
        O....#....
        O.OO#....#
        .....##...
        OO.#O....O
        .O.....O#.
        O.#..O.#.#
        ..O..#O..O
        .......O..
        #....###..
        #OO..#....
    """.trimIndent()

    private val cut = AoCSolutionRepository.findByDay(AoCSolutionRepository.dayByClass(this.javaClass))

    @Test
    fun solution1() {
        assertThat(cut.solve1(input)).isEqualTo("136")
    }

    @Test
    fun solution2() {
        assertThat(cut.solve2(input)).isEqualTo("64")
    }
}
