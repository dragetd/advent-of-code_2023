package net.speciesm.draget.solutions

import net.speciesm.draget.utils.AoCSolutionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {

    private val input = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent()

    private val cut = AoCSolutionRepository.findByDay(AoCSolutionRepository.dayByClass(this.javaClass))

    @Test
    fun solution1() {
        assertThat(cut.solve1(input)).isEqualTo("6440")
    }

    @Test
    fun solution2() {
        assertThat(cut.solve2(input)).isEqualTo("5905")
    }
}
