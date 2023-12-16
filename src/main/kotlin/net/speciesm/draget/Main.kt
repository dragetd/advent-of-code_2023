package net.speciesm.draget

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import net.speciesm.draget.utils.AoCInputRepository
import net.speciesm.draget.utils.AoCSession
import net.speciesm.draget.utils.AoCSolutionRepository

fun main(args: Array<String>) = Main().main(args)

class Main : CliktCommand() {
    val interactiveLogin by option("-i", "--interactive-login", help = "Start interactive login and store the AoC session.").flag()
    val minDay by option("-m", "--min-day", help = "Start solving only from this day onwards.").int().default(1)

    override fun run() {
        val session = AoCSession.getSession(interactiveLogin)

        for (day in minDay..24) {
            val input = try {
                AoCInputRepository.findByDay(day, session)
            } catch (e: Exception) {
                println(e.message)
                continue
            }
            val solution = try {
                AoCSolutionRepository.findByDay(day)
            } catch (e: ClassNotFoundException) {
                println("Day$day: Not yet implemented.")
                continue
            }

            runCatching { solution.solve1(input) }
                .onSuccess { solution1: String -> println("Day$day: Solution1 = $solution1") }
                .onFailure { println("Day$day: part 1 is not yet implemented.") }

            runCatching { solution.solve2(input) }
                .onSuccess { solution2: String -> println("Day$day: Solution2 = $solution2") }
                .onFailure { println("Day$day: part 2 is not yet implemented.") }
        }
    }
}
