package net.speciesm.draget.utils

import net.speciesm.draget.DaySolution
import org.reflections.Reflections
import kotlin.reflect.full.createInstance

object AoCSolutionRepository {
    fun findAllSolutions(): List<DaySolution> {
        val reflections = Reflections("net.speciesm.draget.solutions")
        val dayClasses = reflections.getSubTypesOf(DaySolution::class.java)
        return dayClasses.mapNotNull { dayClass ->
            dayClass.kotlin.objectInstance ?: dayClass.kotlin.createInstance()
        }
    }

    fun dayByClass(someClass: Class<*>): Int {
        val matchResult = Regex("""Day(\d+)\D*""").matchEntire(someClass.simpleName)
            ?: throw IllegalArgumentException("Invalid class name format.")
        return matchResult.groupValues[1].toInt()
    }

    fun findByDay(day: Int): DaySolution {
        val className = "net.speciesm.draget.solutions.Day${String.format("%02d", day)}"
        return Class.forName(className).newInstance() as DaySolution
    }
}
