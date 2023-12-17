package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution
import kotlin.math.round

class Day14 : DaySolution {

    data class Platform(val width: Int, val height: Int) {
        enum class Element(val representation: Char) {
            EMPTY('.'),
            MOVABLE_STONE('O'),
            FIXED_STONE('#'),
            ;

            companion object {
                fun byRepresentation(input: Char): Element {
                    return entries.find { it.representation == input } ?: throw IllegalArgumentException("Invalid input: $input")
                }
            }
        }

        enum class Direction { NORTH, WEST, SOUTH, EAST }

        fun shift(direction: Direction) {
            when (direction) {
                Direction.NORTH -> {
                    for (y in 0 until width) {
                        for (x in 0 until height) {
                            if (data[y][x] == Element.MOVABLE_STONE) moveStone(x, y, direction)
                        }
                    }
                }

                Direction.WEST -> {
                    for (x in 0 until width) {
                        for (y in 0 until height) {
                            if (data[y][x] == Element.MOVABLE_STONE) moveStone(x, y, direction)
                        }
                    }
                }

                Direction.SOUTH -> {
                    for (y in height - 1 downTo 0) {
                        for (x in 0 until width) {
                            if (data[y][x] == Element.MOVABLE_STONE) moveStone(x, y, direction)
                        }
                    }
                }

                Direction.EAST -> {
                    for (x in (width - 1) downTo 0) {
                        for (y in 0 until height) {
                            if (data[y][x] == Element.MOVABLE_STONE) moveStone(x, y, direction)
                        }
                    }
                }
            }
        }

        fun cycle() {
            shift(Direction.NORTH)
            shift(Direction.WEST)
            shift(Direction.SOUTH)
            shift(Direction.EAST)
        }

        private fun moveStone(x: Int, y: Int, direction: Direction) {
            val (stepX, stepY) = when (direction) {
                Direction.NORTH -> 0 to -1
                Direction.WEST -> -1 to 0
                Direction.SOUTH -> 0 to 1
                Direction.EAST -> 1 to 0
            }
            var currentX = x + stepX
            var currentY = y + stepY
            while (currentX in 0 until width && currentY in 0 until height) {
                if (data[currentY][currentX] == Element.FIXED_STONE || data[currentY][currentX] == Element.MOVABLE_STONE) break
                currentX += stepX
                currentY += stepY
            }
            data[y][x] = Element.EMPTY
            data[currentY - stepY][currentX - stepX] = Element.MOVABLE_STONE
        }

        private var data = Array(height) { Array(width) { Element.EMPTY } }

        companion object {
            fun byInput(input: String): Platform {
                val lines: List<String> = input.trim().lines()
                val platform = Platform(lines[0].length, lines.size)
                for (y in 0 until platform.width) {
                    for (x in 0 until platform.height) {
                        platform[y][x] = Element.byRepresentation(lines[y][x])
                    }
                }
                return platform
            }
        }

        private operator fun get(y: Int): Array<Element> = data[y]

        fun getLoadOnDirection(direction: Direction): Long {
            val weightFunction: (Int, Int) -> Long = when (direction) {
                Direction.NORTH -> { _: Int, y: Int -> (height - y).toLong() }
                Direction.WEST -> { x: Int, _: Int -> x.toLong() }
                Direction.SOUTH -> { _: Int, y: Int -> y.toLong() }
                Direction.EAST -> { x: Int, _: Int -> (width - x).toLong() }
            }

            return data.mapIndexed { y, row ->
                row.mapIndexed { x, element ->
                    if (element == Element.MOVABLE_STONE) weightFunction(x, y) else 0L
                }.sum()
            }.sum()
        }

        override fun toString(): String {
            return data.joinToString(separator = "\n") { line ->
                line.map { element ->
                    element.representation
                }.joinToString(separator = "")
            }
        }
    }

    override fun solve1(input: String): String {
        val platform = Platform.byInput(input)
        platform.shift(Platform.Direction.NORTH)
        return platform.getLoadOnDirection(Platform.Direction.NORTH).toString()
    }

    override fun solve2(input: String): String {
        val platform = Platform.byInput(input)

        var currentLoad = -1L
        for (cycle in 1..1000000000) {
            platform.cycle()
            currentLoad = platform.getLoadOnDirection(Platform.Direction.NORTH)
            if (cycle % 1000 == 0) {
                TODO("Giving up")
                println("Done ${round((cycle / 1000000000.0) * 1000L) / 1000}")
//                val newLoad = platform.getLoadOnDirection(Platform.Direction.NORTH)
//                println("Cycle $cycle: $newLoad")
//                if (newLoad == currentLoad) {
//                    currentLoad = newLoad
////                    break
//                } else {
//                    currentLoad = newLoad
//                }
            }
        }
        return currentLoad.toString()
    }
}

