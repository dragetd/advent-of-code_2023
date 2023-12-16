package net.speciesm.draget.utils

import net.speciesm.draget.XDGUtils
import java.io.File
import java.time.LocalDate

object AoCInputRepository {
    private val inputsPath: String = "${XDGUtils.getConfigHome("draget_aoc")}/2023/inputs/"

    init {
        File(inputsPath).mkdirs()
    }

    fun findByDay(day: Int, session: String): String {
        val inputFile = File("$inputsPath/day$day.txt")
        if (LocalDate.of(2023, 12, day).isAfter(LocalDate.now())) {
            throw IllegalArgumentException("Requested day $day is after today, not downloading any input.")
        }

        if (inputFile.exists()) {
            return inputFile.readText()
        }

        println("Input for day $day not yet cached, downloading.")
        val input = AoCWeb.downloadInput(day, session)
        if (input.contains("Please don't repeatedly request this endpoint before it unlocks!")) {
            throw IllegalArgumentException("Server did not return the input.")
        }

        inputFile.writeText(input)
        return input
    }
}
