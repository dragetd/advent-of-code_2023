package net.speciesm.draget.utils

interface Representable {
    val representation: Char
}

inline fun <reified T> playfieldOf(input: String) where T : Representable, T : Enum<T> = Playfield(T::class.java, input)

class Playfield<T>(private val clazz: Class<T>, input: String) where  T : Representable, T : Enum<T> {
    private val data = input.lines().map { it.toMutableList() }.toMutableList()
    private var cachedToString: String? = null

    val width
        get() = data.first().size
    val height
        get() = data.size
    val dimensions
        get() = width to height

    val char = CharAccessor()

    inner class CharAccessor {
        operator fun get(x: Int, y: Int): Char {
            if (y !in data.indices || x !in data[y].indices) throw IndexOutOfBoundsException("Index $x, $y is out of bounds.")
            return data[y][x]
        }

        operator fun set(x: Int, y: Int, value: Char) {
            if (y !in data.indices || x !in data[y].indices) throw IndexOutOfBoundsException("Index $x, $y is out of bounds.")
            data[y][x] = value
            cachedToString = null
        }
    }

    operator fun get(x: Int, y: Int): T {
        if (y !in data.indices || x !in data[y].indices) throw IndexOutOfBoundsException("Index $x, $y is out of bounds.")
        val char = data[y][x]
        return clazz.enumConstants.find { it.representation == char } ?: throw IllegalArgumentException("Invalid input: $char")
    }

    operator fun set(x: Int, y: Int, value: T) {
        if (y !in data.indices || x !in data[y].indices) throw IndexOutOfBoundsException("Index $x, $y is out of bounds.")
        data[y][x] = value.representation
        cachedToString = null
    }

    override fun toString(): String {
        if (cachedToString == null) cachedToString = data.joinToString("\n") { String(it.toCharArray()) }
        return cachedToString!!
    }
}
