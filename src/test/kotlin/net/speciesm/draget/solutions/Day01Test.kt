package net.speciesm.draget.solutions

import net.speciesm.draget.utils.AoCSolutionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

    val input1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    val input2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()

    val cut = AoCSolutionRepository.findByDay(AoCSolutionRepository.dayByClass(this.javaClass))

    @Test
    fun solution1() {
        assertThat(cut.solve1(input1)).isEqualTo("142")
    }

    @Test
    fun solution2() {
        assertThat(cut.solve2(input2)).isEqualTo("281")
    }
}
