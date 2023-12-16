package net.speciesm.draget.solutions

import net.speciesm.draget.utils.AoCSolutionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03Test {

    private val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent()

    private val cut = AoCSolutionRepository.findByDay(AoCSolutionRepository.dayByClass(this.javaClass))

    @Test
    fun solution1() {
        assertThat(cut.solve1(input)).isEqualTo ("4361")
    }

    @Test
    fun solution2() {
        assertThat(cut.solve2(input)).isEqualTo("467835")
    }
}
